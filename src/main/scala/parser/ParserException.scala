package io.github.torsteinvik.flow.parser.lexer

import scala.util.parsing.input._

/** Exception coming from parsing the [[lexer.Token]]s into an [[AST]]
 *  @param msg message of the exception
 *  @param pos position in the input
 */
case class ParserException(msg : String, pos : Position) extends 
    Exception("Syntax error at line " + pos.line + " column " + pos.column + "\n" + pos.longString)
