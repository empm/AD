
Derivado del [Statement](0.%20Statements.md). Hay dos diferencias principales:
- La frase está pre-compilada por el DBMS
- Acepta parámetros

Igual que un [Statement](0.%20Statements.md#Métodos), antes de usarlo, debemos crearlo:
- La creación depende de una conexión abierta
- `PreparedStatement stt = con.prepareStatement(String sql);`

Cuando terminamos, debemos cerrar el [Statement](0.%20Statements.md):
- `.close();`
