-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学校产品', '3', '1', '/hn/schoolProduct', 'C', '0', 'hn:schoolProduct:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '学校产品菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学校产品查询', @parentId, '1',  '#',  'F', '0', 'hn:schoolProduct:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学校产品新增', @parentId, '2',  '#',  'F', '0', 'hn:schoolProduct:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学校产品修改', @parentId, '3',  '#',  'F', '0', 'hn:schoolProduct:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学校产品删除', @parentId, '4',  '#',  'F', '0', 'hn:schoolProduct:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学校产品导出', @parentId, '5',  '#',  'F', '0', 'hn:schoolProduct:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
