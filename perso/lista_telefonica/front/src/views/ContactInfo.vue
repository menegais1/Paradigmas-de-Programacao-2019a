<template>
  <div class="contact">
    <div class="name">Nome: {{contact.name}}</div>
    <div class="phone">Telefone: {{contact.phone}}</div>
    <div class="phone">Endereço: {{contact.address}}</div>
    <div class="phone">Cidade: {{contact.city}}</div>
    <div class="phone">Estado: {{contact.state}}</div>
    <div class="button" @click="back()" >Voltar</div>
  </div>
</template>

<style scoped>
  .contact{
    width: 500px;
    margin: 100px auto;
    border:solid 1px black;
  }
</style>

<script>
  import axios from 'axios'
  export default{
    name: "contactInfo",
    data (){
      return {
        contact: {}
      }
    },
    methods: {
      back: function(){
        this.$router.go(-1)
      }
    },
    mounted (){
    axios.get("http://localhost:4000/app/" + this.$route.params.id).then(response => this.contact = response.data)
    }
  }
</script>