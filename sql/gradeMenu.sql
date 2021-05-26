-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年级', '3', '1', '/hn/grade', 'C', '0', 'hn:grade:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '年级菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年级查询', @parentId, '1',  '#',  'F', '0', 'hn:grade:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年级新增', @parentId, '2',  '#',  'F', '0', 'hn:grade:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年级修改', @parentId, '3',  '#',  'F', '0', 'hn:grade:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年级删除', @parentId, '4',  '#',  'F', '0', 'hn:grade:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年级导出', @parentId, '5',  '#',  'F', '0', 'hn:grade:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
