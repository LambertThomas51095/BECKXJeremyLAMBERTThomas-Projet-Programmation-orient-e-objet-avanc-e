-- CREATE DATABASE secret_agency;

-- USE secret_agency;

CREATE TABLE will (
	code int,
    epitaph VARCHAR(75),
    funerals_type VARCHAR(20),
    CONSTRAINT will_code_pk primary key(code)
);

CREATE TABLE cell (
	name varchar(25),
    address VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20)NOT NULL,
    CONSTRAINT cell_name_pk primary key(name),
    CONSTRAINT address_uk UNIQUE (address),
    CONSTRAINT phone_number_uk UNIQUE (phone_number)
);

CREATE TABLE agent (
	personnal_number int,
    lastname VARCHAR(25) NOT NULL,
    firstname VARCHAR(20) NOT NULL,
    birthdate DATE NOT NULL,
    gsm VARCHAR(20) NOT NULL,
    gender VARCHAR(1) NOT NULL,
    is_alone bit NOT NULL,
    pseudonym VARCHAR(25),
    editorial int,
    affectation varchar(25) NOT NULL,
    CONSTRAINT agent_personnal_Number_pk primary key(personnal_Number),
    CONSTRAINT editorial_fk foreign key(editorial) references will(code),
    CONSTRAINT affectation_fk foreign key(affectation) references cell(name),
    CONSTRAINT gsm_uk UNIQUE (gsm),
    CONSTRAINT pseudonym_uk UNIQUE (pseudonym),
    CONSTRAINT editorial_uk UNIQUE (editorial)
);

CREATE TABLE mission_type (
	code int,
    name varchar(25) NOT NULL,
    CONSTRAINT mission_type_code_pk primary key(code),
    CONSTRAINT name_uk UNIQUE (name)
);

CREATE TABLE mission (
	code int,
    start_date DATE NOT NULL,
    end_date DATE,
    description VARCHAR(75) NOT NULL,
    is_hig_risk bit NOT NULL,
    is_women_only bit NOT NULL,
    category int NOT NULL,
    CONSTRAINT mission_code_pk primary key(code),
    CONSTRAINT category_fk foreign key(category) references mission_type(code),
    CONSTRAINT description_uk UNIQUE (description)
);

CREATE TABLE attribution (
	agent int,
    mission int,
    CONSTRAINT agent_mission_pk primary key(agent,mission),
    CONSTRAINT agent_mission_fk foreign key(agent) references agent(personnal_number),
    CONSTRAINT mission_agent_fk foreign key(mission) references mission(code)
);

CREATE TABLE vehicle (
	personnal_number int,
    type varchar(25) NOT NULL,
    has_weapons bit NOT NULL,
    color varchar(20) NOT NULL,
    `use` int NOT NULL,
    CONSTRAINT personnal_number_pk primary key(personnal_number),
    CONSTRAINT uses_fk foreign key(`use`) references mission(code),
	CONSTRAINT type_uk UNIQUE (type)
);

CREATE TABLE country (
	name varchar(25),
    leader varchar(25) NOT NULL,
    currency varchar(25) NOT NULL,
    government_type varchar(25) NOT NULL,
    position varchar(25),
    CONSTRAINT country_name_pk primary key(name)
);

CREATE TABLE location (
	code int,
    name varchar(25) NOT NULL,
    postal_code int NOT NULL,
    inhabitants_Nb int NOT NULL,
    position varchar(25) NOT NULL,
    CONSTRAINT location_code_pk primary key(code),
    CONSTRAINT position_fk foreign key(position) references country(name)
);

CREATE TABLE mission_location (
	mission int,
    location int,
    CONSTRAINT mission_location_pk primary key(mission,location),
    CONSTRAINT mission_location_fk foreign key(mission) references mission(code),
    CONSTRAINT location_mission_fk foreign key(location) references location(code)
);

CREATE TABLE contact (
	personnal_number int,
    pseudonym VARCHAR(25) NOT NULL,
    gsm VARCHAR(20) NOT NULL,
    CONSTRAINT personnal_number_pk primary key(personnal_number)
);

CREATE TABLE coverage (
	contact int,
    location int,
    CONSTRAINT contact_location_pk primary key(contact,location),
    CONSTRAINT contact_location_fk foreign key(contact) references contact(personnal_number),
    CONSTRAINT location_contact_fk foreign key(location) references location(code)
);

CREATE TABLE language (
	code int,
    name varchar(25) NOT NULL,
    english_name varchar(25) NOT NULL,
    percent_world NUMERIC(5,2),
    CONSTRAINT language_code_pk primary key(code)
);

CREATE TABLE offical_language (
	language int,
    country varchar(25),
    CONSTRAINT language_country_pk primary key(language,country),
    CONSTRAINT language_country_fk foreign key(language) references language(code),
    CONSTRAINT country_language_fk foreign key(country) references country(name)
);

CREATE TABLE ability (
	agent int,
    language int,
    CONSTRAINT agent_language_pk primary key(agent,language),
    CONSTRAINT agent_language_fk foreign key(agent) references agent(personnal_number),
    CONSTRAINT language_agent_fk foreign key(language) references language(code)
);
