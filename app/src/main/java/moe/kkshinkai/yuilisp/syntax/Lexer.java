package moe.kkshinkai.yuilisp.syntax;

public class Lexer {
    private final String src;
    private int ptr = 0;
    private int start = 0;
    private int line = 1;
    private int column = 0;
    private TokenKind kind;

    private int nextChar() {
        var c = src.codePointAt(ptr);

        switch (c) {
            case '\n' -> { line++; column = 0; }
            case '\r' -> {
                if (src.codePointAt(ptr + 1) != '\n') {
                    System.out.println("warning: Yui does not support CR '\\r' as line ending, please use LF '\\n' or CRLF '\\r\\n' instead");
                }
            }
            default -> column++;
        }

        ptr += Character.charCount(c);
        return c;
    }

    private int peekChar() {
        if (ptr >= src.length()) {
            return -1;
        } else {
            return src.codePointAt(ptr);
        }
    }

    public Lexer(String src) {
        this.src = src;
    }

    public TokenKind nextToken() {
        // Skip whitespace
        while (peekChar() != -1 && isWhitespace(peekChar())) {
            nextChar();
        }

        if (peekChar() == -1)
            return kind = TokenKind.EOF;

        start = ptr;
        var c = peekChar();
        switch (c) {
            case '(' -> { nextChar(); return kind = TokenKind.L_PAREN; }
            case ')' -> { nextChar(); return kind = TokenKind.R_PAREN; }
            case ';' -> { skipComment(); return nextToken(); }
            case '"' -> { return lexString(); }
            case '\'' -> { return lexSymbol(); }
            case '+', '-' -> { return lexSign(); }
            default -> {
                if (isDigit(c)) {
                    return lexNumber();
                } else if (isIdentifierStart(c)) {
                    return lexIdentifier();
                } else {
                    System.out.println("error: illegal character '" + Character.toString(c) + "' at line " + line + ", column " + column);
                    skipUnknownToken();
                    return nextToken();
                }
            }
        }
    }

    private TokenKind lexSign() {
        nextChar();

        var c = peekChar();
        if (isDigit(c)) {
            return lexNumber();
        } else if (c == -1 || isIdentifierBody(c) || isDelimiter(c)) {
            return lexIdentifier();
        } else {
            skipUnknownToken();
            System.out.println("error: illegal character '" + Character.toString(c) + "' at line " + line + ", column " + column);
            return nextToken();
        }
    }

    private TokenKind lexNumber() {
        while (isDigit(peekChar())) {
            nextChar();
        }
        if (peekChar() == '.') {
            nextChar();
            while (isDigit(peekChar())) {
                nextChar();
            }
            if (peekChar() == 'e' || peekChar() == 'E') {
                nextChar();
                if (peekChar() == '+' || peekChar() == '-') {
                    nextChar();
                }
                while (isDigit(peekChar())) {
                    nextChar();
                }
            }
            return kind = TokenKind.FLOAT_LITERAL;
        } else {
            return kind = TokenKind.INTEGER_LITERAL;
        }
    }

    private TokenKind lexIdentifier() {
        nextChar(); // Eat identifier start
        while (isIdentifierBody(peekChar())) {
            nextChar();
        }

        if (peekChar() != -1 && !isDelimiter(peekChar())) {
            skipUnknownToken();
            System.out.println("error: identifier '" + src.substring(start, ptr) + "' is not a valid identifier at line " + line + ", column " + column);
            return nextToken();
        }

        var identifier = src.substring(start, ptr);
        return switch (identifier) {
            case "nil" -> kind = TokenKind.NIL_LITERAL;
            case "true" -> kind = TokenKind.TRUE_LITERAL;
            case "false" -> kind = TokenKind.FALSE_LITERAL;
            default -> kind = TokenKind.IDENTIFIER;
        };
    }

    private TokenKind lexSymbol() {
        nextChar(); // Eat '\''

        if (peekChar() == -1) {
            System.out.println("error: symbol with out body at line " + line + ", column " + column);
            return nextToken();
        }

        var c = nextChar();
        if (!isIdentifierStart(c)) {
            System.out.println("error: character '" + nextChar() + "' is not a valid symbol start at line " + line + ", column " + column);
            skipUnknownToken();
            return nextToken();
        }

        while (isIdentifierBody(peekChar())) {
            nextChar();
        }

        if (!isDelimiter(peekChar())) {
            skipUnknownToken();
            System.out.println("error: identifier '" + src.substring(start, ptr) + "' is not a valid identifier at line " + line + ", column " + column);
            return nextToken();
        }

        return kind = TokenKind.SYMBOL_LITERAL;
    }

    private TokenKind lexString() {
        nextChar(); // Eat '"'

        while (peekChar() != '"') {
            if (peekChar() == -1) {
                System.out.println("error: string with out body at line " + line + ", column " + column);
                return nextToken();
            }
            nextChar();
        }

        nextChar(); // Eat '"'
        return kind = TokenKind.STRING_LITERAL;
    }

    private void skipComment() {
        while (peekChar() != -1 && !isLineEnding(peekChar())) {
            nextChar();
        }
    }

    private void skipUnknownToken() {
        // Now the token is broken, skip it until delimiter.
        while (peekChar() != -1 && !isDelimiter(peekChar())) {
            nextChar();
        }
    }

    private boolean isDigit(int c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLetter(int c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    private boolean isIdentifierStart(int c) {
        return c == '!' || c == '$' || c == '%' || c == '^' || c == '&'
            || c == '*' || c == '-' || c == '_' || c == '+' || c == '='
            || c == ':' || c == '~' || c == '<' || c == '>' || c == '.'
            || c == '?' || c == '/' || isLetter(c);
    }

    private boolean isIdentifierBody(int c) {
        return isIdentifierStart(c) || isDigit(c);
    }

    private boolean isDelimiter(int c) {
        return c == '(' || c == ')' || c == ';' || c == '"' || isWhitespace(c);
    }

    private boolean isWhitespace(int c) {
        return c == ' ' || c == '\t' || isLineEnding(c);
    }

    private boolean isLineEnding(int c) {
        return c == '\r' || c == '\n';
    }

    public TokenKind getTokenKind() {
        return kind;
    }

    public String getText() {
        return src.substring(start, ptr);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
