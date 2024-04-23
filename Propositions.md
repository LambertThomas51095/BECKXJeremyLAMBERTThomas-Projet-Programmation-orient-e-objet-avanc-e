# Propositions remplissage BD agence secrète

Ce markdwown contient la suite des propositions pour chacune des tables en SQL qui sera à ajouter

## Agent 

nbLignes : 20 ?

| personnal_number | lastname | firstname | birthdate | gsm | gender | is_Alone | pseudonym[0..1] | editorial[0..1] | affecctation | 
| :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: | 
| 1 | Beckx | Jérémy | 12/01/2003 | 123/45.67.89 | M | false | null | null | IESN | 

## Will

nbLignes : 

| code | epitaph | funerals_type |
| :--: | :--: | :--: |
| 1 | Blabla dead | incineration |

## Cell 

nbLignes : 5-6 ?

| name | address | phone_number |
| :--: | :--: | :--: |
| IESN | boulevard of broken dreams | 81/321.987 |

## Ability 

nbLignes : 

| agent | language |
| :--: | :--: |
| 1 | 1 |


## Language

nbLignes : 10 ?

inspi : https://traduc.com/blog/langues-les-plus-parlees-au-monde/#:~:text=Classement%20des%20langues%20les%20plus%20parl%C3%A9es%20dans%20le,%3A%20258%2C2%20millions%20de%20locuteurs%20...%20More%20items

| code | name | english_name | percent_world[0..1] |
| :--: | :--: | :--: | :--: |
| 1 | francais | french | 3.43 |
| 2 | anglais | english | 31.25 |
| 3 | mandarin | mandarin | 11.94 |
| 4 | hindi | hindi | 7.53 |
| 5 | espagnol | spanish | 6.85 |
| 6 | arabe | arabic |3.43 |
| 7 | russe | russian | 3.23 |
| 8 | portugais | portuguese | 3.22 |
| 9 | japonnais | japanese | 1.63 |
| 10 | allemand | german | 1.57 |

## Attribution

nbLignes : 

| agent | mission |
| :--: | :--: |
| 1 | 1 |

## Mission

nbLignes : 

| code | start_date | end_date[0..1] | description | is_High_Risk | is_Women_Only | category |
| :--: | :--: | :--: | :--: | :--: | :--: | :--: |
| 1 | 05/04/2024 |  | réussir java | true | false | 1 |


## Mission_Type

nbLignes : 5 ?

| code | name |
| :--: | :--: |
| 1 | sabotage |
| 2 | inflitration |
| 3 | assassination |
| 4 | hostage rescue |
| 5 | information retrieval |


## Vehicle

nbLignes : 10 ?  
 
Ref : James Bond, K2000

| personnal_number | type | has_weapons | color | use[0..1] |
| :--: | :--: | :--: | :--: | :--: |
| 1 | Sport car | false | Midnight purple 2 |  |
| 2 | Truck | false | White| 1 |
| 3 | Truck | true | Black|  |
| 4 | SUV | true | Red |  |
| 5 | SUV | false | Blue |  |
| 6 | Bike | false | Cream |  |
| 7 | Bike | true | Light Blue |  |
| 8 | Grand tourer | true | Silver Birch |  |
| 9 | Muscle car | true |  Black |  |
| 10 | Sedan | false |  Brown |  |

## Mission_Location

nbLignes : 

| mission | location |
| :--: | :--: |
| 1 | 1 |

## Location

nbLignes : 

| code | name | postal_Code | inhabitants_nb | position |
| :--: | :--: | :--: | :--: | :--: |
| 1 | Namur | 5100 | 112.065  | Belgium |
| 2 | Bertrix | 6880 | 8 282 | Belgium |
| 3 | Marseille | 13001 | 873 076 | France |
| 4 | Paris | 75001 | 1 133 111 | France |
| 5 | Moscow | 101035 | 13 100 000 | Russia |
| 6 | Birmingham | B | 970 892 | England |
| 7 | Lisbon | 1000-001 | 545 920 | Portugal |
| 7 | London | CR0 | 7 172 091 | England |
| 8 | Tokyo | 2232 | 37 115 035 | Japan |
| 9 | Kyoto | 520-0461-520-0465 | 1 460 000 | Japan |
| 10 | Berlin | 10117 | 3 760 000 | Germany |

## Coverage

nbLignes : 

| location | contact |
| :--: | :--: |
| 1 | 1 |
| 1 | 2 |
| 2 | 4 |
| 3 | 6 |
| 4 | 2 |
| 4 | 7 |
| 5 | 3 |
| 6 | 1 |
| 7 | 7 |
| 7 | 8 |
| 8 | 5 |
| 9 | 1 |
| 10 | 6 |

## Contact

nbLignes : 10 ?

| personnal_number | pseudonym | gsm |
| :--: | :--: | :--: |
| 1 | bananaSplit | 987/23.89.21 |
| 2 | SmoothieFraise | 651/65.43.21 |
| 3 | TennisKiwi | 753/65.37.21 |
| 4 | ApplePhone | 357/65.43.68 |
| 5 | RioWindow | 752/25.43.21 |
| 6 | Berlin | 651/65.01.21 |
| 7 | 007 | 125/89.43.21 |
| 8 | IRA | 051/653.227 |
| 9 | IRA | 051/653.227 |

## Country

nbLignes : 10-15 ? (en fct des langues)

| name | leader | currency | government_type |
| :--: | :--: | :--: | :--: |
| Belgium | Philipe | euro | federal constitutional monarchy |
| Russia | Vladimir Poutine  | russian ruble | federal democratic state |
| Portugal | Marcelo Rebelo de Sousa  | euro | semi-presidential republic |
| France | Emmanuel Macron | euro | republic |
| England | Charles III | pound sterling | parliamentary democracy |
| Japan | Naruhito | yen | constitutional monarchy |
| Germany | Frank-Walter Steinmeier | euro | federal parliamentary republic|

## Official_Language

nbLignes : 

| country | language |
| :--: | :--: |
| France | 1 |
| England | 2 |
| Portugal | 8 |
| Russia | 7 |
| Japan | 9 |
| Germany | 10 |