ciume(anita,alice).
ciume(anita,caren).

pobre(pedro).
pobre(maria).
pobre(bia).
pobre(bernardo).

rico(alice).
rico(henrique).
rico(adriano).
rico(caren).

insano(adriano).
insano(maria).

santaMaria(pedro,segunda).
santaMaria(pedro,terca).
santaMaria(pedro,quinta).
santaMaria(bernardo,segunda).
santaMaria(bernardo,terca).
santaMaria(bernardo,quinta).
santaMaria(caren,quinta).
santaMaria(bia,quinta).
santaMaria(adriano,quarta).
santaMaria(maria,terca).
santaMaria(maria,quarta).
santaMaria(maria,quinta).

portoAlegre(caren,segunda).
portoAlegre(caren,terca).
portoAlegre(caren,quarta).
portoAlegre(pedro,quarta).
portoAlegre(bernardo,quarta).
portoAlegre(henrique,terca).
portoAlegre(bia,terca).
portoAlegre(bia,quarta).
portoAlegre(alice,terca).
portoAlegre(alice,quarta).

apartamento(pedro,sexta).
apartamento(caren,sexta).
apartamento(henrique,segunda).
apartamento(henrique,quarta).
apartamento(henrique,quinta).
apartamento(henrique,sexta).
apartamento(bia,segunda).
apartamento(bia,sexta).
apartamento(adriano,quinta).
apartamento(adriano,sexta).
apartamento(alice,segunda).
apartamento(alice,quinta).
apartamento(alice,sexta).
apartamento(bernardo,sexta).
apartamento(maria,segunda).
apartamento(maria,sexta).

motivo(P) :- pobre(P);insano(P);ciume(anita,P).

rouboBastao(P) :- portoAlegre(P, quinta);santaMaria(P, quarta).

rouboMartelo(P) :- apartamento(P, quarta);apartamento(P, quinta).

arma(P) :- rouboBastao(P);rouboMartelo(P).

chave(bia).
chave(P) :- santaMaria(P,segunda);portoAlegre(P, terca).

acesso(P) :- arma(P), chave(P), (apartamento(P,quinta); apartamento(P,sexta)).

assassino(P) :- motivo(P), acesso(P),!.