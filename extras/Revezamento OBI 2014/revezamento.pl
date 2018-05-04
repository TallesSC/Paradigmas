% https://olimpiada.ic.unicamp.br/static/extras/obi2014/provas/ProvaOBI2014_f1i1.pdf
% Revezamento
% 
%   Oito alunos – Beto, Dulce, Guto, Júlia, Kelly, Neto, Silvia e Vivian decidiram tentar quebrar o recorde
% da tradicional prova de revezamento e resistência de natação que acontece todos os anos na escola.
% Nessa prova, cada um dos oito competidores da equipe deve nadar mil metros, em estilo livre, na
% forma de revezamento: cada nadador cai na piscina para nadar apenas uma vez, um de cada vez. O
% objetivo é que todos nadem no menor tempo possível. Depois de muita discussão, os competidores
% decidiram que a ordem em que cairão na piscina deve obedecer às seguintes condições:

% • Silvia não nada por último.
% • Vivian nada após Júlia e Neto nadarem.
% • O primeiro a nadar é ou Beto ou Dulce.
% • Guto nada antes de Júlia, com exatamente uma pessoa nadando entre eles.
% • Kelly nada antes de Neto, com exatamente duas pessoas nadando entre eles.

r0(L) :-
    member(beto,L),
    member(dulce,L),
    member(guto,L),
    member(julia,L),
    member(kelly,L),
    member(neto,L),
    member(silvia,L),
    member(vivian,L).

r1(L) :-
    nth1(Ps,L,silvia),
    Ps < 8.

r2(L) :-
    nth1(Pj,L,julia),
    nth1(Pn,L,neto),
    nth1(Pv,L,vivian),
    Pv > Pj,
    Pv > Pn.

r3(L) :-
    nth1(1,L,beto); nth1(1,L,dulce).

r4(L) :-
    Pj == Pg + 2,
    nth1(Pg,L,guto),
    nth1(Pj,L,julia). 

r5(L) :-
    Pn == Pk + 3,
    nth1(Pk,L,kelly),
    nth1(Pn,L,neto).

questao21(L) :-
    revezamento(L),
    L = [dulce,kelly,silvia,guto,neto,beto,julia,vivian];
    L = [dulce,silvia,kelly,guto,neto,julia,beto,vivian];
    L = [beto,kelly,silvia,guto,neto,julia,vivian,dulce]; % RESPOSTA CORRETA (C)
    L = [beto,guto,kelly,julia,dulce,neto,vivian,silvia];
    L = [beto,silvia,dulce,kelly,vivian,guto,neto,julia].

revezamento(L) :-
    L = [_,_,_,_,_,_,_,_],
    r0(L),
    r1(L),
    r2(L),
    r3(L),
    r4(L),
    r5(L).

%Questão 22. Se Vivian nada antes de Beto, então
%qual dos seguintes pode ser o segundo a nadar?
%(A) Silvia
%(B) Júlia
%(C) Neto
%(D) Guto [Correta]
%(E) Dulce

%Questão 23. Qual das seguintes alternativas é necessariamente verdadeira?
%(A) O mais cedo que Vivian pode nadar é em oitavo lugar.
%(B) O mais cedo que Júlia pode nadar é em quinto lugar.
%(C) O mais cedo que Kelly pode nadar é em terceiro lugar.
%(D) O mais cedo que Silvia pode nadar é em terceiro lugar.
%(E) O mais cedo que Neto pode nadar é emquinto lugar. [CORRETA]

%Questão 24. Guto pode nadar em qualquer das ordens abaixo, exceto:
%(A) sexto lugar [CORRETA]
%(B) quinto lugar
%(C) quarto lugar
%(D) terceiro lugar
%(E) segundo lugar

%Questão 25. Se Silvia nada antes de Júlia, então o mais cedo que Júlia pode nadar é em:
%(A) segundo lugar
%(B) terceiro lugar
%(C) quarto lugar
%(D) quinto lugar [CORRETA]
%(E) sexto lugar
