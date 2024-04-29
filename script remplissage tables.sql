INSERT INTO cell (name,address,phone_number)
VALUES ("Informatique","1 Rue Morte","080/123.456"),
("Marin","47 Rue Pas là","081/153.546"),
("Ninja","325 Rue de la joute","082/86.26.57"),
("Mécanique"," 5 Rue inconnu","081/568.424"),
("Chimiste","96 Place partout","085/98.87.65");

INSERT INTO will(code,epitaph,funerals_type)
VALUES (1,"Marine la marine","Incinération");

Insert into agent(personnal_number,lastname,firstname,birthdate,gsm,gender,is_alone,editorial,affectation)
VALUES (1,"|nefB","~qhtfJ","2000-03-28","469/23.12.56","M",false,null,"Informatique"),
(2,"zwieobL","xepqiT","2002-10-15","470/78.35.82","M",true,null,"Informatique"),
(3,'ou}vv%eo"fD',"jrltbM","2001-05-21","471/13.82.45","F",true,1,"Marin");

INSERT INTO language (code,name,english_name,percent_world)
VALUES (1,"français","french",3.43),
(2,"anglais","english",31.25),
(3,"mandarin","mandarin",11.94),
(4,"hindi","hindi",7.53),
(5,"espagnol","spanish",6.85),
(6,"arabe","arabic",3.43),
(7,"russe","russian",3.23),
(8,"portugais","portuguese",3.22),
(9,"japonnais","japanese",1.63),
(10,"allemand","german",1.57);

INSERT INTO ability (agent,language)
VALUES (1,1),
(1,2),
(1,5),
(1,9),
(2,1),
(2,2),
(3,2),
(3,6);

INSERT INTO mission_type (code, name)
VALUES (1,"sabotage"),
(2,"infiltration"),
(3,"assassinat"),
(4,"libération d'otages"),
(5,"récolte d'informations");

INSERT INTO mission (code,start_date,end_date,description,is_high_risk,is_women_only,category)
VALUES (1,"2024-04-05",null,"Réussir java",false,false,5);

INSERT INTO attribution (agent,mission)
VALUES (1,1),
(2,1);

INSERT INTO country (name,leader,currency,government_type)
VALUES ("Belgique","Philipe","euro","monarchie"),
("Russie","Vladimir Poutine","rouble russe","état fédéral"),
("Portugal","Marcelo Rebelo de Sousa","euro","république"),
("France","Emmanuel Macron","euro","république"),
("Angleterre","Charles III","livre sterling","démocratie"),
("Japon","Naruhito","yen","monarchie"),
("Allemagne","Frank-Walter Steinmeier","euro","république");

INSERT INTO location (code,name,postal_code,inhabitants_nb,position)
VALUES (1,"Namur",5100,112065,"Belgique"),
(2,"Bertrix",6880,8282,"Belgique"),
(3,"Marseille",13000,873076,"France"),
(4,"Paris",75000,1133111,"France"),
(5,"Moscou",101035,13100000,"Russie"),
(6,"Birmingham",2,970892,"Angleterre"),
(7,"Lisbonne",1000001,545920,"Portugal"),
(8,"Londres",1,7172091,"Angleterre"),
(9,"Kyoto",5200461,1460000,"Japon"),
(10,"Berlin",10117,3760000,"Allemagne");

INSERT INTO mission_location (mission,location)
VALUES (1,1);

INSERT INTO contact (personnal_number,pseudonym,gsm)
VALUES (1,"bananaSplit","987/23.89.21"),
(2,"SmoothieFraise","651/65.43.21"),
(3,"TennisKiwi","753/65.37.21"),
(4,"ApplePhone","357/65.43.68"),
(5,"RioWindow","752/25.43.21"),
(6,"Berlin","651/65.01.21"),
(7,"007","125/89.43.21"),
(8,"IRA","051/653.227");

INSERT INTO coverage (contact,location)
VALUES (1,1),
(2,4),
(3,2),
(4,7),
(5,7),
(6,10),
(6,9),
(7,8),
(7,6),
(8,6),
(8,5),
(2,3);