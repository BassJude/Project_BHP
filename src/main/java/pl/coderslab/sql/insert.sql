
INSERT INTO questions (answer1,answer2,answer3,answer4,good_answer,question) VALUES
("Odpowiedź A","Odpowiedź B","Odpowiedź C", "Odpowiedź D", "A","Pytanie 1"),
("Odpowiedź A","Odpowiedź B","Odpowiedź C", "Odpowiedź D", "A","Pytanie 2"),
("Odpowiedź A","Odpowiedź B","Odpowiedź C", "Odpowiedź D", "C","Pytanie 3"),
("Odpowiedź A","Odpowiedź B","Odpowiedź C", "Odpowiedź D", "D","Pytanie 4"),
("Odpowiedź A","Odpowiedź B","Odpowiedź C", "Odpowiedź D", "A","Pytanie 5"),
("Odpowiedź A","Odpowiedź B","Odpowiedź C", "Odpowiedź D", "B","Pytanie 6"),
("Odpowiedź A","Odpowiedź B","Odpowiedź C", "Odpowiedź D", "C","Pytanie 7");

INSERT INTO users (login,password,firstName,lastName,city, street,number_of_home,email,last_test,passed,admin) VALUES
("sowa","123456","Adam","Pierzchała","Warszawa","Czapelska","46/98","email@email.pl",now(),true,true ),
("krowa","123498","Michał","Kowalski","Kraków","Wiktorska","10/95","email@email.pl",now(),false ,true ),
("kogut","123495489","Ewa","Nowak","Wrocław","Grenadierów","225/65","email@email.pl",now(),true,false ),
("kot","123494654","Paulina","Podsiadło","Gdańsk","Mysia","335/8","email@email.pl",now(),false ,false ),
("pies","12341235","Antek","Wiebrzbicki","Szczecin","Krypska","30/2","email@email.pl",now(),true,true );

/*
INSERT INTO `plan` (`id`, `name`, `description`, `created`, `admin_id`) VALUES
(1, 'Plan Jarski', NULL, '2018-10-17 14:27:05', 1);

INSERT INTO `recipe` (`id`, `name`, `ingredients`, `description`, `created`, `updated`, `admin_id`, `preparation_time`, `preparation`) VALUES
(1, 'Przepis 1', 'sałata', 'Opis przepisu 1', '2018-10-17 00:00:00', '2018-10-17 14:24:44', 1, 30,'Sałaty dokładnie myję i jeszcze dokładniej suszę - nie powinno być na liściach żadnych kropelek wody.'),
(2, 'Przepis 2', 'sałata', 'Opis przepisu 2', '2018-10-16 00:00:00', '2018-10-17 14:24:44', 1, 30,'Sałaty dokładnie myję i jeszcze dokładniej suszę - nie powinno być na liściach żadnych kropelek wody.'),
(3, 'Przepis 3', 'sałata', 'Opis przepisu 3', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 30,'Sałaty dokładnie myję i jeszcze dokładniej suszę - nie powinno być na liściach żadnych kropelek wody.');
*/
-- create - tworzy tabele na podstawie adnotacji encji, nie nadpisuje zmian
--           none - nie wykonuje żadnych operacji
--           drop-and-create - usunie a następnie utworzy, przy pracy uzywamy tego
--           drop - usunie elementy bazy danych zgodne z adnotacjami
--           update - !!! nie zawsze poprawnie zmieni