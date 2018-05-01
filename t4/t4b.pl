% Olimpíada Brasileira de Informática – OBI2016 – Iniciação Nível 1 – Fase 1
% Nova cerca
% João está contruindo uma cerca com postes e traves, como nos diagramas abaixo.
% Diagrama 1: 2 postes, 3 traves.
% Diagrama 2: 3 postes, 6 traves.
% Diagrama 3: 4 postes, 9 traves.
% Questão 10. Cada poste custa R$ 10,00 e cada trave custa R$ 5,00. Qual o custo de uma cerca com oito postes?
% (A) R$ 80,00
% (B) R$ 105,00
% (C) R$ 120,00
% (D) R$ 205,00
% (E) R$ 215,00
% Questão anulada, resposta correta deveria ser: R$ 185.

cerca(C,P) :-
	T is (P-1)*3,
	Cp is P*10,
	Ct is T*5,
	C is Cp + Ct.

