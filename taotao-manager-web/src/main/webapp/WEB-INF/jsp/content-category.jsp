<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">
    </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	$("#contentCategory").tree({
		url : '/content/category/list',
		animate: true,//节点展示时，有动画效果
		method : "GET",
		//展示菜单事件
		onContextMenu: function(e,node){
            e.preventDefault();
          	//包当前节点变为选中状态，$(this)转换为jquery对象
            $(this).tree('select',node.target);
            //展示菜单
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        //当新增节点 编辑后触发(光标离开新增节点时)
        onAfterEdit : function(node){
        	var _tree = $(this);
        	if(node.id == 0){
        		// 新增节点，向后台提供数据
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				target : node.target,
            				id : data.data.id//将数据库的主键id去覆盖新增节点时默认的id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		//修改节点名称
        		$.post("/content/category/update",{id:node.id,name:node.text});
        	}
        }
	});
});
function menuHandler(item){
	//获取菜单树形对象
	var tree = $("#contentCategory");
	//获取当前选中的节点
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		//如果点击的是添加操作,在你当前选中的节点下新增节点，你选中的当前节点，就是将要新增节点的父节点
		tree.tree('append', {
            parent: (node?node.target:null),
            data: [{
                text: '新建分类',
                id : 0,//默认值
                parentId : node.id
            }]
        }); 
		//找id为0的节点
		var _node = tree.tree('find',0);
		//然后选中当前节点(也就是新增节点)，并把变为可编辑状态
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		//如果点击是重命名，把选中的节点变为可编辑状态
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			//如果点击确定，r就为true
			if(r){
				//parentId:node.parentId，节点信息只有三个属性id，text，state，拿不到parentId信息，所以不要此参数
				//注意：1.如果删除后要判断父节点下是否还有其他子节点(如果有，不变isparent，如果没有就要把isparent变为false)
				//2.如果删除的是父节点，有两种做法：1）如果删除的是父节点且父节点下还有子节点，提示 该节点下有子节点不能删除 。2）把父节点及其关联的子节点都删除
				$.post("/content/category/delete/",{id:node.id},function(){
					tree.tree("remove",node.target);
				});	
			}
		});
	}
}
</script>