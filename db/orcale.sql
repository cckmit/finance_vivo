-- Create table 字典信息表
create table FM_DICT
(
    id          VARCHAR2(32) not null,
    dict_name   VARCHAR2(100) not null,
    dict_code   VARCHAR2(100) not null,
    description VARCHAR2(255),
    del_flag    NUMBER(10),
    create_by   VARCHAR2(32),
    create_time DATE,
    update_by   VARCHAR2(32),
    update_time DATE,
    type        NUMBER(10)
)
comment on table FM_DICT
  is '字典信息表';
-- Add comments to the columns
comment on column FM_DICT.id
  is '主键ID';
comment on column FM_DICT.dict_name
  is '字典名称';
comment on column FM_DICT.dict_code
  is '字典编码';
comment on column FM_DICT.description
  is '描述';
comment on column FM_DICT.del_flag
  is '删除状态';
comment on column FM_DICT.create_by
  is '创建人';
comment on column FM_DICT.create_time
  is '创建时间';
comment on column FM_DICT.update_by
  is '更新人';
comment on column FM_DICT.update_time
  is '更新时间';
comment on column FM_DICT.type
  is '字典类型0为string,1为number';

-- create table 字典信息明细表
create table FM_DICT_ITEM
(
    id          VARCHAR2(32) not null,
    dict_id     VARCHAR2(32),
    item_text   VARCHAR2(100) not null,
    item_value  VARCHAR2(100) not null,
    description VARCHAR2(255),
    sort_order  NUMBER(10),
    status      NUMBER(10),
    create_by   VARCHAR2(32),
    create_time DATE,
    update_by   VARCHAR2(32),
    update_time DATE
)
-- Add comments to the table
comment on table FM_DICT_ITEM
  is '字典明细表';
-- Add comments to the columns
comment on column FM_DICT_ITEM.id
  is '字典明细表ID';
comment on column FM_DICT_ITEM.dict_id
  is '字典id';
comment on column FM_DICT_ITEM.item_text
  is '字典项文本';
comment on column FM_DICT_ITEM.item_value
  is '字典项值';
comment on column FM_DICT_ITEM.description
  is '描述';
comment on column FM_DICT_ITEM.sort_order
  is '排序';
comment on column FM_DICT_ITEM.status
  is '状态（1启用 0不启用）';
comment on column FM_DICT_ITEM.create_by
  is '创建人';
comment on column FM_DICT_ITEM.create_time
  is '创建时间';
comment on column FM_DICT_ITEM.update_by
  is '更新人';
comment on column FM_DICT_ITEM.update_time
  is '更新时间';