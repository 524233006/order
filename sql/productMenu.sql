-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品', '3', '1', '/hn/product', 'C', '0', 'hn:product:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '产品菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品查询', @parentId, '1',  '#',  'F', '0', 'hn:product:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品新增', @parentId, '2',  '#',  'F', '0', 'hn:product:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品修改', @parentId, '3',  '#',  'F', '0', 'hn:product:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品删除', @parentId, '4',  '#',  'F', '0', 'hn:product:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品导出', @parentId, '5',  '#',  'F', '0', 'hn:product:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
