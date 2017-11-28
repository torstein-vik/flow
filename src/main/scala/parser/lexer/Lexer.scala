package io.github.torsteinvik.flow.parser.lexer


object Lexer {
    def getTokens(file : String) : Seq[Token] = {
        return Seq.empty[Token]
    }
    
    private val identifier = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toSet
    private val numeric = ('0' to '9').toSet
    private def getToken(string : String) : Option[Token] = string match {
        case "Semantic" => Some(Token.Semantic)
        case "Define" => Some(Token.Define)
        case ":" => Some(Token.Colon)
        case ";" => Some(Token.Semicolon)
        case "=>" => Some(Token.Machine)
        case "->" => Some(Token.Flow)
        case "(" => Some(Token.TupleOpen)
        case ")" => Some(Token.TupleClose)
        case "," => Some(Token.TupleSeparator)
    }
}
