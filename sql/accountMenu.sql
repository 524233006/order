-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('粉丝', '3', '1', '/wx/account', 'C', '0', 'wx:account:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '粉丝菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('粉丝查询', @parentId, '1',  '#',  'F', '0', 'wx:account:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('粉丝新增', @parentId, '2',  '#',  'F', '0', 'wx:account:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('粉丝修改', @parentId, '3',  '#',  'F', '0', 'wx:account:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('粉丝删除', @parentId, '4',  '#',  'F', '0', 'wx:account:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('粉丝导出', @parentId, '5',  '#',  'F', '0', 'wx:account:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
