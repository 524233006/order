-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级', '3', '1', '/hn/classes', 'C', '0', 'hn:classes:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '班级菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级查询', @parentId, '1',  '#',  'F', '0', 'hn:classes:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级新增', @parentId, '2',  '#',  'F', '0', 'hn:classes:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级修改', @parentId, '3',  '#',  'F', '0', 'hn:classes:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级删除', @parentId, '4',  '#',  'F', '0', 'hn:classes:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级导出', @parentId, '5',  '#',  'F', '0', 'hn:classes:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
