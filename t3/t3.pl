repete(0, _, []).
repete(N, C, L) :- 
 N > 0,
 L = [C | T],
 N1 is N - 1,
 repete(N1, C, T).

zeroInit(L) :- L = [0|_].

has5(L) :- L = [_,_,_,_,_].

hasN(L,N) :- length(L,N).

potN0(-1,[]).
potN0(N,L) :- 
    L = [A|T],
    A is 2**N,
    N1 is N-1,
    potN0(N1,T).
