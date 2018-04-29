%  Uma banda formada por alunos e alunas da escola estão gravando um CD com exatamente sete músicas 
% distintas – S, T, V, W, X, Y e Z. Cada música ocupa exatamente uma das sete faixas contidas no CD. 
% Algumas das músicas são sucessos antigos de rock; outras são composições da própria banda.
%  As seguintes restrições devem ser obedecidas:
  
%  1. S ocupa a quarta faixa do CD.
%  2. Tanto W como Y precedem S no CD (ou seja, W e Y estão numa faixa que é tocada antes de S no CD).
%  3. T precede W no CD (ou seja, T está numa faixa que é tocada antes de W).
%  4. Um sucesso de rock ocupa a sexta faixa do CD.
%  5. Cada sucesso de rock é imediatamente precedido no CD por uma composição da banda (ou seja, 
% no CD cada sucesso de rock toca imediatamente após uma composição da banda).
%  6. Z é um sucesso de rock.

r0(M) :-
    member(mus(s,comp),M) ; member(mus(s,rock),M),
    member(mus(t,comp),M) ; member(mus(t,rock),M),
    member(mus(v,comp),M) ; member(mus(v,rock),M),
    member(mus(w,comp),M) ; member(mus(w,rock),M),
    member(mus(x,comp),M) ; member(mus(x,rock),M),
    member(mus(y,comp),M) ; member(mus(y,rock),M),
    member(mus(z,rock),M).
    
r1(M) :-
    nth1(4,M,mus(s,_)).

r2(M) :-
    Pw < 4,
    Py < 4,
    nth1(Pw,M,mus(w,_)),
    nth1(Py,M,mus(y,_)).
	
r3(M) :-
    nth1(Pw,M,mus(w,_)),
    Pt < Pw,
    nth1(Pt,M,mus(t,_)).

r4(M) :-
    nth1(6,M,mus(_,rock)).

r5(M) :-
    nextto(mus(_,comp), mus(_,rock), M).

cdindependente(M) :-
    M = [_,_,_,_,_,_,_],
    r0(M),
    r1(M),
    r2(M),
    r3(M),
    r4(M),
    r5(M).
