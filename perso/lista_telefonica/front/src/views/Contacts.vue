<template>
    <div class="home">
        <div class="input-group">
            <input class="input" type="text" placeholder="Pesquise por nome" v-model="name"/>
            <input class="input" type="text" placeholder="Pesquise por telefone" v-model="phone"/>
        </div>
        <ul class="contacts">
            <li class="contact" v-for="contact in contacts">
                <div class="name">Nome: {{contact.name}}</div>
                <div class="phone">Telefone: {{contact.phone}}</div>
                <div class="button" @click="contactInfo(contact.id)">Open</div>
            </li>
        </ul>
    </div>
</template>

<script>

    import axios from 'axios'

    export default {
        name: 'Contacts',
        data() {
            return {
                list: [],
                name: '',
                phone: ''
            }
        },
        methods: {
            contactInfo: function (_id) {
                this.$router.push({path: "/contactInfo/" + _id})
            },
            filterByName: function (list, name) {
                if (!name) return list
                return list.filter((contact) =>
                    this.name && contact.name.startsWith(this.name))
            },
            filterByPhone: function (list, phone) {
                if (!phone) return list
                return list.filter((contact) =>
                    phone && contact.phone.toString().startsWith(this.phone))
            }
        },
        computed: {
            contacts: function () {
                if (!this.name && !this.phone) return this.list
                return this.filterByPhone(this.filterByName(this.list, this.name), this.phone)
            }
        },
        mounted() {
            axios.get("http://localhost:4000/app").then(response => this.list = response.data)
        }
    }
</script>
