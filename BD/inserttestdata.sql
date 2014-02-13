use ift604_projet; 

insert into MAGASIN (MAGID, MAGNOM, MAGLATITUDE, MAGLONGITUDE, MAGNOCIVIQUE, MAGNOMRUE, MAGVILLE, MAGPROVINCE, MAGCODEPOSTAL) values (1, 'IGA', 45.387109, -71.922277, 3050, "King Ouest", "Sherbrooke", "QC", "J2K 3G8");
insert into MAGASIN (MAGID, MAGNOM, MAGLATITUDE, MAGLONGITUDE, MAGNOCIVIQUE, MAGNOMRUE, MAGVILLE, MAGPROVINCE, MAGCODEPOSTAL) values (2, 'Maxi', 45.3971671, -71.932239, 3050, "Portland", "Sherbrooke", "QC", "J1L 2J8");
insert into MAGASIN (MAGID, MAGNOM, MAGLATITUDE, MAGLONGITUDE, MAGNOCIVIQUE, MAGNOMRUE, MAGVILLE, MAGPROVINCE, MAGCODEPOSTAL) values (3, 'Metro', 45.387146, -71.922276, 1230, "King Est", "Sherbrooke", "QC", "J4K 5G6");
insert into MAGASIN (MAGID, MAGNOM, MAGLATITUDE, MAGLONGITUDE, MAGNOCIVIQUE, MAGNOMRUE, MAGVILLE, MAGPROVINCE, MAGCODEPOSTAL) values (4, 'Canadian Tire', 45.367133, -71.912245, 3233, "King Ouest", "Sherbrooke", "QC", "J2H 3G8");
insert into MAGASIN (MAGID, MAGNOM, MAGLATITUDE, MAGLONGITUDE, MAGNOCIVIQUE, MAGNOMRUE, MAGVILLE, MAGPROVINCE, MAGCODEPOSTAL) values (5, 'Future Shop', 45.366133, -71.916245, 1020, "King Ouest", "Sherbrooke", "QC", "J2K 3H8");

insert into CATEGORIE_ARTICLE (CATID, CATNOM) values (1, 'Outils');
insert into CATEGORIE_ARTICLE (CATID, CATNOM) values (2, 'Viande');
insert into CATEGORIE_ARTICLE (CATID, CATNOM) values (3, 'Fruits et légumes');
insert into CATEGORIE_ARTICLE (CATID, CATNOM) values (4, 'Électronique');

insert into ARTICLE (ARTID, CATID, ARTNOM) values (1, 1, 'Marteau');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (2, 1, 'Tourne vis');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (3, 2, 'Boeuf haché');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (4, 2, 'Poitrines de poulet');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (5, 4, 'Lecteur blu-ray');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (6, 3, 'Pommes');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (7, 3, 'Oranges');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (8, 3, 'Bananes');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (9, 4, 'Télévision');
insert into ARTICLE (ARTID, CATID, ARTNOM) values (10, 4, 'Lecteur VHS');

insert into VENDS (ARTID, MAGID, VENPRIX) values (1, 4, 15);

insert into VENDS (ARTID, MAGID, VENPRIX) values (2, 4, 18);

insert into VENDS (ARTID, MAGID, VENPRIX) values (3, 1, 6.99);
insert into VENDS (ARTID, MAGID, VENPRIX) values (3, 2, 7.99);
insert into VENDS (ARTID, MAGID, VENPRIX) values (3, 3, 6.49);

insert into VENDS (ARTID, MAGID, VENPRIX) values (4, 1, 8);
insert into VENDS (ARTID, MAGID, VENPRIX) values (4, 2, 9);
insert into VENDS (ARTID, MAGID, VENPRIX) values (4, 3, 8);

insert into VENDS (ARTID, MAGID, VENPRIX) values (5, 5, 99.99);

insert into VENDS (ARTID, MAGID, VENPRIX) values (6, 1, 2);
insert into VENDS (ARTID, MAGID, VENPRIX) values (6, 2, 2);
insert into VENDS (ARTID, MAGID, VENPRIX) values (6, 3, 3);

insert into VENDS (ARTID, MAGID, VENPRIX) values (7, 1, 6);
insert into VENDS (ARTID, MAGID, VENPRIX) values (7, 2, 3);
insert into VENDS (ARTID, MAGID, VENPRIX) values (7, 3, 3);

insert into VENDS (ARTID, MAGID, VENPRIX) values (8, 1, 1.51);
insert into VENDS (ARTID, MAGID, VENPRIX) values (8, 2, 1);
insert into VENDS (ARTID, MAGID, VENPRIX) values (8, 3, 4);

insert into VENDS (ARTID, MAGID, VENPRIX) values (9, 5, 1098.02);

insert into VENDS (ARTID, MAGID, VENPRIX) values (10, 5, 4999.99);