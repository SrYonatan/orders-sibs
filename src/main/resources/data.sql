INSERT INTO ITENS (name) VALUES ('arroz');
INSERT INTO Itens (name) VALUES ('feijão');
INSERT INTO Itens (name) VALUES ('batata');
INSERT INTO Itens (name) VALUES ('macarrão');

INSERT INTO Users (name, email) VALUES ('adrian', 'a@gmail.com');
INSERT INTO Users (name, email) VALUES ('barbara', 'b@gmail.com');
INSERT INTO Users (name, email) VALUES ('melissa', 'm@gmail.com');
INSERT INTO Users (name, email) VALUES ('jorge', 'j@gmail.com');

INSERT INTO Stock (item_id, quantity) VALUES ((SELECT ID FROM ITENS WHERE NAME = 'arroz'), 50);
INSERT INTO Stock (item_id, quantity) VALUES ((SELECT ID FROM ITENS WHERE NAME = 'feijão'), 50);
INSERT INTO Stock (item_id, quantity) VALUES ((SELECT ID FROM ITENS WHERE NAME = 'batata'), 50);
INSERT INTO Stock (item_id, quantity) VALUES ((SELECT ID FROM ITENS WHERE NAME = 'macarrão'), 50);

INSERT INTO Stock_Movements (creation_Date, quantity, item_id) VALUES ('20/06/2024', 6, (SELECT ID FROM ITENS WHERE NAME = 'arroz'));
INSERT INTO Stock_Movements (creation_Date, quantity, item_id) VALUES ('19/06/2024', 5, (SELECT ID FROM ITENS WHERE NAME = 'feijão'));
INSERT INTO Stock_Movements (creation_Date, quantity, item_id) VALUES ('18/06/2024', 9, (SELECT ID FROM ITENS WHERE NAME = 'batata'));
INSERT INTO Stock_Movements (creation_Date, quantity, item_id) VALUES ('16/06/2024', 3, (SELECT ID FROM ITENS WHERE NAME = 'macarrão'));

INSERT INTO Orders (creation_Date, quantity, item_id, user_id, status) VALUES ('20/06/2024', 6, (SELECT ID FROM ITENS WHERE NAME = 'arroz'), 1, 'Incomplete');
INSERT INTO Orders (creation_Date, quantity, item_id, user_id, status) VALUES ('19/06/2024', 5, (SELECT ID FROM ITENS WHERE NAME = 'feijão'), 2, 'Incomplete');
INSERT INTO Orders (creation_Date, quantity, item_id, user_id, status) VALUES ('18/06/2024', 9, (SELECT ID FROM ITENS WHERE NAME = 'batata'), 4, 'Incomplete');
INSERT INTO Orders (creation_Date, quantity, item_id, user_id, status) VALUES ('16/06/2024', 3, (SELECT ID FROM ITENS WHERE NAME = 'macarrão'), 3, 'Incomplete');
