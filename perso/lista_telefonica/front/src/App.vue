<template>
  <div id="app">
    <router-view/>
  </div>
</template>


<style>

  .input-group{
    text-align: center;
    margin: 10px auto;
  }

  .input{
    font-size:22px;
    padding: 5px;
    margin:3px;
    border:none;
    border-bottom: solid 1px blue;
  }

  .input:focus{
    outline: none;
    caret-color: red;
    border-bottom: solid 1px darkred;
  }

  .contacts{
    list-style: none;
    background-color: #e0e5e5;
    padding: 0px;
    margin: 25px auto;
    width: 500px;
    border:solid 2px black;
  }

  .contact{
    margin: 0px;
    padding: 20px;
    align-text: center;
    border-bottom: solid 1px black
  }

  .button{
    padding:8px;
    margin: 0px auto;
    border:solid 1px black;
    width:50px;
    text-align: center;
  }

  .button:hover{
    background-color: #cacece;
    cursor: pointer;
  }

  .name{
    font-size:18px;
    padding:3px;
  }

  .phone{
    font-size:18px;
    padding:3px;

  }

</style>
