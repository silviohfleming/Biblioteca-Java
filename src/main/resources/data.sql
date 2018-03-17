insert into Usuario (id, username, password) values
	(1,'teste', '$2a$10$dF4sa0W6vVnrlTcswhWEzOc.1IO2GV8DF8wujDejbkJPQQR6emylG');

insert into Autor (id, nome) values
	(1,'Carlos Drummond de Andrade'),
	(2,'Clarice Lispector'),
	(3,'Ariano Suassuna');

insert into Livro (id, nome, quantidade_paginas, autor_id) values
	(1, 'Sentimento do Mundo', 100,1),
	(2,'Perto do Coração Selvagem', 200,2),
	(3,'Ariano Suassuna', 300,3);

insert into Emprestimo (id, data_devolucao, data_emprestimo, livro_id, usuario_id) values
	(1, '2018-03-17 15:31:28.534', '2018-03-17 15:31:22.882',1,1),
	(2, null, '2018-03-17 15:31:22.882',3,1);