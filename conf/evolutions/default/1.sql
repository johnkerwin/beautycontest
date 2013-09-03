# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table game (
  id                        bigint not null,
  name                      varchar(255),
  done                      boolean,
  due_date                  timestamp,
  constraint pk_game primary key (id))
;

create table guess (
  id                        bigint not null,
  value                     integer,
  game_id                   bigint,
  player_id                 bigint,
  due_date                  timestamp,
  constraint uq_guess_1 unique (game_id,player_id),
  constraint pk_guess primary key (id))
;

create table player (
  id                        bigint not null,
  name                      varchar(255),
  create_date               timestamp,
  constraint pk_player primary key (id))
;

create sequence game_seq;

create sequence guess_seq;

create sequence player_seq;

alter table guess add constraint fk_guess_game_1 foreign key (game_id) references game (id) on delete restrict on update restrict;
create index ix_guess_game_1 on guess (game_id);
alter table guess add constraint fk_guess_player_2 foreign key (player_id) references player (id) on delete restrict on update restrict;
create index ix_guess_player_2 on guess (player_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists game;

drop table if exists guess;

drop table if exists player;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists game_seq;

drop sequence if exists guess_seq;

drop sequence if exists player_seq;

