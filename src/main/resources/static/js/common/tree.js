function initSelectableTree(){
		return $('#treeview-selectable').treeview({
				data: defaultData,
				multiSelect: $('#chk-select-multi').is(':checked'),
				enableLinks:true,
				state:{
					expanded:true
				},
				onNodeSelected: function(event, node) {
					$('#selectable-output').prepend('<p>' + node.text + ' was selected</p>');
				},
				onNodeUnselected: function (event, node) {
					$('#selectable-output').prepend('<p>' + node.text + ' was unselected</p>');
				}
		});
};
var $selectableTree = initSelectableTree();

function findSelectableNodes() {
	return $selectableTree.treeview('search', [ $('#input-select-node').val(), { ignoreCase: false, exactMatch: false } ]);
};

var selectableNodes = findSelectableNodes();

$('#chk-select-multi:checkbox').on('change', function () {
	console.log('multi-select change');
	$selectableTree = initSelectableTree();
	selectableNodes = findSelectableNodes();          
});

// Select/unselect/toggle nodes
$('#input-select-node').on('keyup', function (e) {
	selectableNodes = findSelectableNodes();
	$('.select-node').prop('disabled', !(selectableNodes.length >= 1));
});

$('#btn-select-node.select-node').on('click', function (e) {
	$selectableTree.treeview('selectNode', [ selectableNodes, { silent: $('#chk-select-silent').is(':checked') }]);
});

$('#btn-unselect-node.select-node').on('click', function (e) {
	$selectableTree.treeview('unselectNode', [ selectableNodes, { silent: $('#chk-select-silent').is(':checked') }]);
});

$('#btn-toggle-selected.select-node').on('click', function (e) {
	$selectableTree.treeview('toggleNodeSelected', [ selectableNodes, { silent: $('#chk-select-silent').is(':checked') }]);
});

//设置默认不展开节点 展开当前节点
function setExpandNodes(column){
		console.log(defaultData);
		//var allNodes = $selectableTree.treeview('checkAll', { silent:true });
		//console.log(allNodes)
		var expandedNodeId = getExpandedNodeId(column)
		var expandedNode = $selectableTree.treeview('getNode',[expandedNodeId,'id']);
		var currentNodeId = getCurrentNodeId(column)
		$selectableTree.treeview("collapseAll");
		var currentNode = $selectableTree.treeview('getNode',[currentNodeId,'id']);//得到第当前节点
		console.log(currentNode);
		expandedNode.state.expanded = true;//展开某节点
		$selectableTree.treeview("selectNode", currentNode);//将当前节点设置为选中状态**
}
setExpandNodes(columnData);

//获取需要展开的节点
function getExpandedNodeId(column){
	//首先获取所有的tag
	var result =  ""
	$("#treeview-selectable li").each(function(){
		var nodeId = $(this).attr("data-nodeid");
		currentNode = $selectableTree.treeview('getNode',[nodeId,'id'])
		var href = $(this).find("a").attr("href");
		if(href!=null && href != ""){
			var columnId = href.split("?")[1].split("=")[1]
			if(columnId == column.id){
				while(true){
					if(currentNode.parentId==undefined){
						result = currentNode.nodeId;
						break;
					}
					currentNode = $selectableTree.treeview('getNode',[currentNode.parentId,'id'])
				}
			}
			
		}
	})
	return result;
}

//获取当前节点ID
function getCurrentNodeId(column){
	//首先获取所有的tag
	var result =  ""
	var size = 0;
	$("#treeview-selectable li").each(function(){
		var nodeId = $(this).attr("data-nodeid");
		var href = $(this).find("a").attr("href");
		if(href!=null && href != ""){
			var columnId = href.split("?")[1].split("=")[1]
				if(columnId == column.id){
					result = nodeId;
				}
		}
		size++;
	})
	return result;
}

//添加click function
/* $("#treeview-selectable ul li").each(function(index,element){
		$(this).click(function(){
			window.location.href = $(this).find("a").attr("href");
		})
}) */

