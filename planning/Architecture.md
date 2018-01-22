# Main Architecture of Flow

## Project compilation process

1. Compilation of project folder -> all subfiles of "./src/syntax/flow" folder individually parsed.

2. Compilation of project folder -> all subfiles of "./src/lib/flow" folder individually parsed, using syntax declarations at the start of the file.

3. All identifier declarations are listed (with scope), and identifier use is linked to the declarations.

4. All identifier declarations are typechecked in terms of identifiers, and added to some form of centralized repository of classes and machines.

5. This is passed to the inference engine, which infers all functions (using imports) (failing if there is ambiguity).

