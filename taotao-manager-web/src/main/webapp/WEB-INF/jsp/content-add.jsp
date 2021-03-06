<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- kindeditor富文本编辑器的样式 -->
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<!-- kindeditor富文本编辑器的js脚本，经过压缩和加密的 -->
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<!-- kindeditor富文本编辑器的语言环境js脚本 -->
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="contentAddForm" class="itemForm" method="post">
		<input type="hidden" name="categoryId"/>
	    <table cellpadding="5">
	        <tr>
	            <td>内容标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>内容子标题:</td>
	            <td><input class="easyui-textbox" type="text" name="subTitle" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>内容描述:</td>
	            <td><input class="easyui-textbox" name="titleDesc" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input>
	            </td>
	        </tr>
	         <tr>
	            <td>URL:</td>
	            <td><input class="easyui-textbox" type="text" name="url" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>图片:</td>
	            <td>
	                <input type="hidden" name="pic" />
	                <a href="javascript:void(0)" class="easyui-linkbutton onePicUpload">图片上传</a>
	            </td>
	        </tr>
	        <tr>
	            <td>图片2:</td>
	            <td>
	            	<input type="hidden" name="pic2" />
	            	<a href="javascript:void(0)" class="easyui-linkbutton onePicUpload">图片上传</a>
	            </td>
	        </tr>
	        <tr>
	            <td>内容:</td>
	            <td>
	            	<!-- kindeditor富文本编辑器的一个初始化控件，需要原生的html(textarea)作为载体，实际展示时不显示，时隐藏的，富文本编辑器的内容数据会放在这个html标签中 -->
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="content"></textarea>
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="contentAddPage.submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="contentAddPage.clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var contentAddEditor ;
	//页面初始化完成后会调用的方法
	$(function(){
		//初始化富文本编辑器
		contentAddEditor = TT.createEditor("#contentAddForm [name=content]");
		//初始化单图片上传组件
		TT.initOnePicUpload();
		//拿到你在树形控件中选择的节点对象的id,将其赋值给隐藏域
		$("#contentAddForm [name=categoryId]").val($("#contentCategoryTree").tree("getSelected").id);
	});
	
	var contentAddPage  = {
			//提交
			submitForm : function (){
				if(!$('#contentAddForm').form('validate')){
					$.messager.alert('提示','表单还未填写完成!');
					return ;
				}
				//富文本编辑器数据同步
				contentAddEditor.sync();
				
				$.post("/content/save",$("#contentAddForm").serialize(), function(data){
					if(data.status == 200){
						$.messager.alert('提示','新增内容成功!');
						//数据网格重新加载
    					$("#contentList").datagrid("reload");
						//当前窗口关闭
    					TT.closeCurrentWindow();
					}
				});
			},
			//重置
			clearForm : function(){
				$('#contentAddForm').form('reset');
				contentAddEditor.html('');
			}
	};
</script>
