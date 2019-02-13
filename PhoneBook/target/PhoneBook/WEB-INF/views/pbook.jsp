<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home Page</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
 <link rel="stylesheet" href=css/pbook.css>
 
 
 <script type="text/javascript" src="js/angular.min.js"></script>
 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
  
  <script type="text/javascript" src="js/contact.js"></script>
 
</head>
<body>
<div data-ng-app="PbookApp" data-ng-controller="PbookController" data-ng-init="init();">
<div class="topnav" id="con_grp_nav">
  <a class={{contactActive}} data-ng-click="onContacts();" data-ng-model="mcontact" data-ng-value="contacts">Contacts</a>
  <a class={{groupActive}}  data-ng-click="onGroups();" data-ng-model="mgroup" data-ng-value="groups">Groups</a>
  <!-- <a style="float:right" href="#news"  onclick="msg()" data-ng-click="logingout();">Add {{addVal}}</a> -->
  <a  style="float:right"  href="logout" >LogOut</a>
 </div>
 <h1 class="alert alert-danger" role="alert" data-ng-hide="errorcontact">{{errorAddingContact}}
</h1>
 
 <div data-ng-model="availableContacts" data-ng-hide="allContacts">
 
 <div class="card w-50" data-ng-repeat="availContact in availableContacts | orderBy : 'contact_name'" >
  <div class="card-body" data-ng-init="initContacts(availContact.contact_id);">
    <h3 class="card-title">{{availContact.contact_name}}</h3>
    <h5 class="card-text">{{availContact.ph_num_primary}}
     <!--  <a style="float:right" href="#" class="btn btn-primary">...</a> -->
     <button  style="float:right" data-ng-click="toggleDrop($index);" class="dropbtn">...</button>
     </h5>
     <div class="cdropdown" style="float:right">
  <div id="myDropdown" class="cdropdown-content" data-ng-hide="contactKeyArray[$index].value">
    <a data-ng-click="deleteContact($index);">Delete Contact</a>
    <!-- <a data-ng-click="addContactToGroup($index);">Add To Group</a> type="button" class="btn btn-primary"-->
    <a data-ng-click="addContactToGroup($index);"  data-toggle="modal" data-target="#exampleModalLong1">
  Add To Group
</a>
    
  </div>
</div>
  
  </div>
  </div>
  </div>
    
<!-- Modal -->
<div class="modal fade" id="exampleModalLong1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Groups Available</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <div class="modal-body">
      <form data-ng-submit="addingAContactToGroup();" method="post">
      <div data-ng-model="availableGroups" data-ng-hide="hidegroups">
       <div data-ng-repeat="availGroup in availableGroups | orderBy : 'group_name'">
    <div data-ng-init="initConGroups(availGroup.group_id);">
   
       <input type="checkbox" data-ng-model="contactgroupKey[$index].value" data-ng-checked="contactgroupKey[$index].value" />{{availGroup.group_name}} <br />
    </div>
    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" data-ng-click="addingAContactToGroup();" data-dismiss="modal" type="submit" value="submit" class="btn btn-primary">Done</button>
      </div>
      </form>
    </div>
  </div>
</div>
    </div>
  <div data-ng-model="availableGroups" data-ng-hide="allGroups">
 
 <div class="card w-50" data-ng-repeat="availGroup in availableGroups | orderBy : 'group_name'" >
  <div class="card-body" data-ng-init="initGroups(availGroup.group_id);">
    <h3 class="card-title">{{availGroup.group_name}}</h3>
    <h5 class="card-text">{{availGroup.user_id}}
     <!--  <a style="float:right" href="#" class="btn btn-primary">...</a> -->
     <button  style="float:right" data-ng-click="toggleGroupsDrop($index);" class="dropbtn">...</button>
     </h5>
     <div class="cdropdown" style="float:right">
  <div id="myDropdown" class="cdropdown-content container" data-ng-hide="groupKeyArray[$index].value">
    <a data-ng-click="deleteGroup($index);">Delete Group</a>
    <!--  <a data-ng-click="addContactToGroup($index);"  data-toggle="modal" data-target="#exampleModalLong1"> -->
    <a data-ng-click="viewContactsInGrp($index);" data-toggle="modal" data-target="#exampleModalLong2">Contacts</a>
  </div>
</div>
  
  </div>
  </div>
  </div>
  
  <!-- Modal -->
<div class="modal fade" id="exampleModalLong2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Contacts In Group</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <div class="modal-body">
      <form data-ng-submit="addingAContactToGroup();" method="post">
       <div data-ng-repeat="cingrp in coninGrps | orderBy : 'contact_name'">
   <!--  <div data-ng-init="initConGroups(availGroup.group_id);"> -->
   
   <h1>{{cingrp.contact_name}} :: {{cingrp.ph_num_primary}}</h1><br />
      <!--  <input type="checkbox" data-ng-model="contactgroupKey[$index].value" data-ng-checked="contactgroupKey[$index].value" />{{availGroup.group_name}} <br /> -->
   <!--  </div> -->
    </div>
      
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      <!--   <button type="button" data-ng-click="addingAContactToGroup();" data-dismiss="modal" type="submit" value="submit" class="btn btn-primary">Done</button> -->
      </div>
      </form>
    </div>
  </div>
</div>
    </div>
  
 <button class="open-button" id="mydd" data-ng-click="formOpen(addVal);"  >Add {{addVal}}</button>
<div class="form-popup" id="myForm" data-ng-hide="myForm" data-ng-model="myForm">
  <form data-ng-submit="add_new_contact();" method="post" class="popup-form-container">
    <h1>Add Contact</h1>

    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter Name" name="conName"  data-ng-model="contact.conName" required>

    <label for="num1"><b>Number</b></label>
    <input type="text" placeholder="Enter Phone Number" name="numPrimary" data-ng-model="contact.numPrimary" required>
    <label for="num2"><b>Number</b></label>
    <input type="text" placeholder="Enter Phone Number" name="numSecondary" data-ng-model="contact.numSecondary">
	<label for="num3"><b>Number</b></label>
    <input type="text" placeholder="Enter Phone Number" name="numAdditional" data-ng-model="contact.numAdditional">
    
	<label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="conEmail" data-ng-model="contact.conEmail" >
	<label for="bday"><b>BirthDay</b></label>
    <input type="date" placeholder="Enter Name" name="conDob" data-ng-model="contact.conDob" >
    <button type="submit" value="submit" class="btn">Done</button>
    <button type="button" class="btn cancel" data-ng-click="closeForm();">Cancel</button>
  </form>
  </div>
<div class="form-popup" id="myCForm" data-ng-hide="myCForm" data-ng-model="myCForm">
  <form data-ng-submit="add_new_group();" method="post" class="popup-group-form-container">
    <h1>Add Group</h1>

    <label for="grpname"><b>Group Name</b></label>
    <input type="text" placeholder="Enter Group Name" data-ng-model="group.groupName" name="groupName" required>

    <button type="submit" value="submit" class="btn">Done</button>
    <button type="button" class="btn cancel" data-ng-click="closeCForm();">Cancel</button>
  </form>
  </div>


 </div>
</body>
</html>