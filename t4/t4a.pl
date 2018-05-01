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

isRock(_).
isComp(_).

r0(M) :-
    member(s,M),
    member(t,M),
    member(v,M),
    member(w,M),
    member(x,M),
    member(y,M),
    member(z,M),
    isRock(s) ; isComp(s),
    isRock(t) ; isComp(t),
    isRock(v) ; isComp(v),
    isRock(w) ; isComp(w),
    isRock(x) ; isComp(x),
    isRock(y) ; isComp(y),
    isRock(z) ; isComp(z).

r1(M) :-
    nth1(4,M,s).

r2(M) :-
    nth1(Ps,M,s),
    nth1(Pw,M,w),
    nth1(Py,M,y),
    Pw < Ps,
    Py < Ps.
	
r3(M) :-
    nth1(Pw,M,w),
    nth1(Pt,M,t),
    Pt < Pw.

r4(M) :-
    nth1(6,M,A),
    isRock(A).

r5(M) :-
    isRock(A),
    isComp(B),
    nextto(B,A,M).

r6(M,K) :-
    member(K,M),
    isRock(K).

questao11(M) :-
    M = [t,w,v,s,y,x,z];
    M = [v,y,t,s,w,z,x];
    M = [x,y,w,s,t,z,s];
    M = [y,t,w,s,x,z,v];
    M = [z,t,x,w,v,y,s].

cdindependente(M) :-
    M = [_,_,_,_,_,_,_],
    r0(M),
    r1(M),
    r2(M),
    r3(M),
    r4(M),
    r5(M),
    r6(M,z),
	questao11(M).

%  Questão 11. Qual das seguintes alternativas poderia
% ser a ordem das músicas no CD, da primeira
% para a sétima faixa?
%
% (A) T, W, V, S, Y, X, Z
% (B) V, Y, T, S, W, Z, X
% (C) X, Y, W, S, T, Z, S
% (D) Y, T, W, S, X, Z, V  [CORRETA]
% (E) Z, T, X, W, V, Y, S    
% 
    
    
    
    
