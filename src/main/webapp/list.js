/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(e){
$('#delete').click(function(){
    $( "#deleteModal" ).dialog({
        height: 140,
        modal: true
    });
});

$('#deleteBtn').click(function(){
    $('#deleteBtn').val($('#deleteId').val());
    
});
$('#update').click(function(){
    $( "#updateModal" ).dialog({
        height: 140,
        modal: true
    });
});

$('#updateBtn').click(function(){
    $('#updateBtn').val($('#updateName').val());
    
});
$('#create').click(function(){
    $( "#createModal" ).dialog({
        height: 140,
        modal: true
    });
});

$('#createBtn').click(function(){
    $('#createBtn').val($('#createName').val());
    
});
});