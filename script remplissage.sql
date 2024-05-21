    INSERT INTO cell (name,address,phone_number)
    VALUES ("Informatique","1 Rue Morte","080/123.456"),
    ("Marin","47 Rue Pas là","081/153.546"),
    ("Ninja","325 Rue de la joute","082/86.26.57"),
    ("Mécanique"," 5 Rue inconnu","081/568.424"),
    ("Chimiste","96 Place partout","085/98.87.65");

    INSERT INTO will(epitaph,funerals_type)
    VALUES ("Marine la marine","Incinération"),
    ("Discret tel l'ombre","Crémation");

    Insert into agent(lastname,firstname,birthdate,gsm,gender,is_alone,pseudonym,editorial,affectation)
    VALUES ("࠯ࠡ࠘࠙ࠕ","࠱ࠤࠛࠧ࠙ࠝ","2000-03-28","0469/23.12.56","M",false,"࠮ࠠ࠮ࠬ࠙ࠤࠛ࠙ࠣࠖ",null,"Informatique"),
    ("࠭ࠪࠜ࠘ࠢࠕࠟ","ࠫ࠘ࠣࠤࠜࠧ","2002-10-15","0470/78.35.82","M",true,null,null,"Informatique"),
    ("ࠢࠨ࠰ࠩࠩ߸࠘ࠢߵ࠙ࠗ","ࠝࠥࠟࠧࠕࠠ","2001-05-21","0471/13.82.45","F",true,"ࠝࠥࠗࠚࠗࠢ",1,"Marin"),
    ("ࠞ࠭࠭࠭ࠜࠚࠣࠕࠩ","ࠤ࠙ࠟ","2000-02-13","0469/77.53.09","M",true,"࠱ࠬࠝ࠮ࠠ࠙",null,"Mécanique"),
    ("ࠜࠟࠧࠕࠠ","ࠛࠨࠣࠥ","1997-05-20","0470/153.759","M",false,null,null,"Chimiste"),
    ("ࠫࠝࠜ࠘ࠢࠕࠗ","ࠫ࠘ࠣࠤࠜࠧ","1999-01-08","0471/978.312","M",true,null,2,"Ninja");

    INSERT INTO language (code,name,english_name,percent_world)
    VALUES (1,"Français","french",3.43),
    (2,"Anglais","english",31.25),
    (3,"Mandarin","mandarin",11.94),
    (4,"Hindi","hindi",7.53),
    (5,"Espagnol","spanish",6.85),
    (6,"Arabe","arabic",3.43),
    (7,"Russe","russian",3.23),
    (8,"Portugais","portuguese",3.22),
    (9,"Japonnais","japanese",1.63),
    (10,"Allemand","german",1.57);

    INSERT INTO ability (agent,language)
    VALUES (1,1),
    (1,2),
    (1,5),
    (1,9),
    (2,1),
    (2,2),
    (3,2),
    (3,6),
    (4,2),
    (4,8),
    (4,3),
    (5,2),
    (5,5),
    (5,7),
    (6,5),
    (6,7),
    (6,9),
    (6,10);

    INSERT INTO mission_type (code, name)
    VALUES (1,"Sabotage"),
    (2,"Infiltration"),
    (3,"Assassinat"),
    (4,"Libération d'otages"),
    (5,"Récolte d'informations");

    INSERT INTO mission (code,start_date,end_date,description,is_high_risk,is_women_only,category)
    VALUES (1,"2024-04-05",null,"Réussir java",false,false,5),
    (2,"2015-10-04",null,"Arrêter Lyustifer Safin",true,false,3),
    (3,"2020-03-15","2023-01-10","Défaire un complot",true,true,5),
    (4,"2021-11-01","2024-03-12","Démanteler le gang",true,true,2),
    (5,"2023-10-01","2023-10-15","Inspecter le bateau 787-B",true,false,4),
    (6,"2019-10-10",null,"Combattre le capitaine crochet",true,false,3);

    INSERT INTO vehicle (personnal_number,type,has_weapons,color,`use`)
    VALUES (1,"Sportive",false,"Midnight purple 2",5),
    (2,"Camionnette",false,"Blanc",null),
    (3,"SUV",true,"Bleu",null),
    (4,"Moto",false,"Rouge",null),
    (5,"Grand Tourisme",true,"Silver Birch",2),
    (6,"Muscle car",true,"Noir",4);

    INSERT INTO attribution (agent,mission)
    VALUES (1,1),
    (1,2),
    (1,5),
    (2,1),
    (2,6),
    (3,3),
    (3,4),
    (4,2),
    (4,6),
    (5,2),
    (5,3),
    (6,5),
    (6,6);

    INSERT INTO country (name,leader,currency,government_type)
    VALUES ("Belgique","Philipe","euro","monarchie"),
    ("Russie","Vladimir Poutine","rouble russe","état fédéral"),
    ("Portugal","Marcelo Rebelo de Sousa","euro","république"),
    ("France","Emmanuel Macron","euro","république"),
    ("Angleterre","Charles III","livre sterling","démocratie"),
    ("Japon","Naruhito","yen","monarchie"),
    ("Allemagne","Frank-Walter Steinmeier","euro","république");

    INSERT INTO official_language(country,language)
    VALUES ("France",1),
    ("Angleterre",2),
    ("Portugal",8),
    ("Russie",7),
    ("Japon",9),
    ("Allemagne",10);

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
    VALUES (1,1),
    (2,8),
    (2,4),
    (3,10),
    (4,6),
    (5,9),
    (6,7);

    INSERT INTO contact (personnal_number,pseudonym,gsm)
    VALUES (1,"࠱ࠥࠧࠪࠬ࠙ࠥࠗࠣࠕࠕ","0987/23.89.21"),
    (2,"ࠦࠠࠢࠧ࠙ࠕ","0651/65.43.21"),
    (3,"ࠌࠄࠃ","0753/65.37.21"),
    (4,"࠲࠭ࠞࠢࠤࠣࠖ","0357/65.43.68"),
    (5,"ࠥࠪࠠࠝࠬࠣࠖ","0752/25.43.21"),
    (6,"࠶ࠡࠩࠩࠩ߸ࠫࠟࠩ࠙ࠣ","0651/65.01.21"),
    (7,"ࠦ࠳ࠨࠟ࠯ࠢ߻ࠟࠢࠠࠫࠥࠤࠡࠦ","0125/89.43.21"),
    (8,"ࠜࠢࠥࠤࠔ","051/653.227");

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