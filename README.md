# LogParse

Para a resolu��o do problema "Quake log parser", foi feita a varredura do arquivo "games.log" linha por linha e fazendo a incrementa��o dos objetos espec�ficos (Player, Game).

Foi feito o tratamento de cada objeto para a listagem de Ranking e informa��es de cada Game.

Testes unit�rio foram feitos utilizando JUnit4.

Para busca de Game por ID, foi utilizado API/REST feita com Java + SpringBoot.

Busca: http://localhost:8080/api/game/(idGame)