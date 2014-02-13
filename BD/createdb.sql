/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-10-28 13:20:30                          */
/*==============================================================*/
/*create database if not exists ift604_projet;

use ift604_projet;

CREATE USER 'java'@'localhost' IDENTIFIED BY 'abc123';
GRANT ALL ON ift604_projet.* TO 'java'@'localhost' IDENTIFIED BY 'abc123';*/

drop table if exists VENDS;

drop table if exists ARTICLE;

drop table if exists CATEGORIE_ARTICLE;

drop table if exists MAGASIN;



/*==============================================================*/
/* Table: ARTICLE                                               */
/*==============================================================*/
create table ARTICLE
(
   ARTID                int not null,
   CATID                int,
   ARTNOM               varchar(64) not null,
   primary key (ARTID)
);

/*==============================================================*/
/* Table: CATEGORIE_ARTICLE                                     */
/*==============================================================*/
create table CATEGORIE_ARTICLE
(
   CATID                int not null,
   CATNOM               varchar(64) not null,
   primary key (CATID)
);

/*==============================================================*/
/* Table: MAGASIN                                               */
/*==============================================================*/
create table MAGASIN
(
   MAGID                int not null,
   MAGNOM               varchar(64) not null,
   MAGLATITUDE          float not null,
   MAGLONGITUDE         float not null,
   MAGNOCIVIQUE			int not null,
   MAGNOMRUE            varchar(64) not null,
   MAGVILLE             varchar(32) not null,
   MAGPROVINCE			varchar(2) not null,
   MAGCODEPOSTAL        varchar(8) not null,
   primary key (MAGID)
);

/*==============================================================*/
/* Table: VENDS                                                 */
/*==============================================================*/
create table VENDS
(
   ARTID                int not null,
   MAGID                int not null,
   VENPRIX              float not null,
   primary key (ARTID, MAGID)
);

alter table ARTICLE add constraint FK_RELATIONSHIP_1 foreign key (CATID)
      references CATEGORIE_ARTICLE (CATID) on delete restrict on update restrict;

alter table VENDS add constraint FK_VENDS foreign key (ARTID)
      references ARTICLE (ARTID) on delete restrict on update restrict;

alter table VENDS add constraint FK_VENDS2 foreign key (MAGID)
      references MAGASIN (MAGID) on delete restrict on update restrict;

