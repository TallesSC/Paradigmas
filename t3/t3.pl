repete(0, _, []).
repete(N, C, L) :- 
 N > 0,
 L = [C | T],
 N1 is N - 1,
 repete(N1, C, T).

% 1.Defina um predicado zeroInit(L) que é verdadeiro se L for uma lista que inicia com o número 0 (zero).
zeroInit(L) :- L = [0|_].

% 2.Defina um predicado has5(L) que é verdadeiro se L for uma lista de 5 elementos. Resolva este exercício sem usar um predicado auxiliar.
has5(L) :- L = [_,_,_,_,_].

% 3.Defina um predicado hasN(L,N) que é verdadeiro se L for uma lista de N elementos.
hasN(L,N) :- length(L,N).

% 4.Defina um predicado potN0(N,L), de forma que L seja uma lista de potências de 2, com expoentes de N a 0.
potN0(-1,[]).
potN0(N,L) :- 
    L = [A|T],
    A is 2**N,
    N1 is N-1,
    potN0(N1,T).
    
% 5.Defina um predicado zipmult(L1,L2,L3), de forma que cada elemento da lista L3 seja o produto dos elementos de L1 e L2 na mesma posição do elemento de L3.
zipmult([],[],[]).
zipmult(L1,L2,L3) :-
    L1 = [A|T],
    L2 = [B|K],
    X is A*B,
    L3 = [X|Y],
    zipmult(T,K,Y).

% 6.Defina um predicado potencias(N,L), de forma que L seja uma lista com as N primeiras potências de 2, sendo a primeira 2^0 e assim por diante.
potencias2(E,E,[]).
potencias2(E,N,L) :-
    H is 2**E,
    L = [H|T],
    E1 is E+1,
    potencias2(E1,N,T).
potencias(N,L) :-
    potencias2(0,N,L).
 
% 7. Defina um predicado positivos(L1,L2), de forma que L2 seja uma lista só com os elementos positivos de L1.
positivos([],[]).
positivos(L1,L2) :-
    L1 = [H|T],
    H > 0,
    X is H,
    L2 = [X|Y],
    positivos(T,Y).
    
positivos(L1,L2) :-
    L1 = [_|T],
    positivos(T,L2).


% 8.Considere que L1 e L2 sejam permutações de uma lista de elementos distintos, sem repetições. Sabendo disso, defina um predicado mesmaPosicao(A,L1,L2) para verificar se um elemento A está na mesma posição nas listas L1 e L2. 
%mesmaPosicao(_,[],[]).
mesmaPosicao(A,[A|_],[A|_]).
mesmaPosicao(A,L1,L2) :-
    L1 = [_|Y],
    L2 = [_|N],
    mesmaPosicao(A,Y,N).


% 9.Dada uma lista de N alunos, deseja-se escolher NP alunos (NP < N) para formar uma comissão. Para isso, defina um predicado comissao(NP,LP,C), que permita gerar as possíveis combinações C com NP elementos da lista LP.
comissao(0,_,[]).
comissao(NP,LP,C) :-
    C = [H|T],
	NP > 0,
    NP2 is NP-1,
    comissao2(H,LP,Z),
    comissao(NP2,Z,T).

comissao2(A,[A|B],B).
comissao2(X,[_|B],R) :- 
    comissao2(X,B,R).

% 10.
azulejos(1,1).
azulejos(0,0).
azulejos(NA,NQ) :-
    X is floor(sqrt(NA))**2,
    NA2 is NA-X,
    azulejos(NA2,NQ2),
    NQ is NQ2+1.
