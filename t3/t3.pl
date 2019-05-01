

impar(N) :- A is mod(N,2), A = 1.
par(N) :- A is mod(N,2), A = 0.

hasN([],0).
hasN([H|T],N) :- X is N - 1, hasN(T,X). 

inc99([],[]).
inc99([H1|T1],[H2|T2]) :- X is H1 + 99, H2 = X, inc99(T1,T2).

incN([],[],N).
incN([H1|T1],[H2|T2],N) :- X is H1 + N, H2 = X, incN(T1,T2,N).

comment([],[]).
comment([H1|T1],[H2|T2]) :- string_concat("%%",H1,H2), comment(T1,T2). 

onlyEven([],[]).
onlyEven([H1|T1],[H2|T2]) :- par(H1), H2 = H1, onlyEven(T1,T2). 
onlyEven([H1|T1],[H2|T2]) :- impar(H1), onlyEven(T1,[H2|T2]).

countdown(0,[]).
countdown(N,[H1|T1]) :- N > 0,H1 = N, X is N-1, countdown(X, T1).

nRandoms(0,[]).
nRandoms(N,[H1|T1]) :-random(1,1000,K),H1 = K,X is N - 1,nRandoms(X, T1).


potN0(-1,[]).
potN0(N,[H1|T1]) :- K is 2 ** N,
H1 = K,
X is N - 1,
potN0(X, T1).

zipmult([],[],[]).
zipmult([H1|T1],[H2|T2],[H3|T3]) :- H3 is H1 * H2, zipmult(T1,T2,T3).

potencias(0,L) :- L = [].
potencias(N, [H1|T1]) :- potenciasaux(N, [H1|T1], N).

potenciasaux(N,[],0).
potenciasaux(N, [H1|T1], N1) :- X is N - N1, H1 is 2 ** X,K is N1 - 1, potenciasaux(N, T1 , K).

cedulas(0,[],[]).
cedulas(V,[H1|T1],[H2|T2]) :- X is div(V,H1), H2 = X, K is V - (X * H1), cedulas(K,T1,T2). 