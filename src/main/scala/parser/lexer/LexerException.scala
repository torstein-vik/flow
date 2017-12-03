package io.github.torsteinvik.flow.parser.lexer

import scala.util.parsing.input._

/** Exception coming from lexing the input
 *  @param msg message of the exception
 *  @param pos position in the input
 */
case class LexerException(msg : String, pos : Position) extends 
    Exception("Syntax error at line " + pos.line + " column " + pos.column + "\n" + pos.longString)
