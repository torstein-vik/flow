package io.github.torsteinvik.flow.parser.lexer


object Lexer {
    def getTokens(file : String) : Seq[Token] = {
        return Seq.empty[Token]
    }
    
    private val identifier = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toSet
    private val numeric = ('0' to '9').toSet
    
    import Token._
    private def getToken(string : String) : Option[Token] = string match {
        
        
        case "Semantic" => Some(Semantic)
        case "Define" => Some(Define)
        case ":" => Some(Colon)
        case ";" => Some(Semicolon)
        case "=>" => Some(Machine)
        case "->" => Some(Flow)
        case "(" => Some(TupleOpen)
        case ")" => Some(TupleClose)
        case "," => Some(TupleSeparator)
        case str if str.forall(numeric.contains(_)) => Some(new Identifier(str) with Numeric)
        case str if str.forall(identifier.contains(_)) => Some(new Identifier(str))
        case _ => None
    }
}
