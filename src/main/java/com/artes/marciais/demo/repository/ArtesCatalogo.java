package com.artes.marciais.demo.repository;

import java.util.List;

import com.artes.marciais.demo.model.Destaque;
import com.artes.marciais.demo.model.Disciplina;
import com.artes.marciais.demo.model.MartialArt;

/**
 * Dados de exemplo das artes marciais site's.
 *
 * O conteudo fica hard-coded de proposito: o foco do site e a apresentacao
 * do material, e nao a persistencia.
 */
final class ArtesCatalogo {

	private ArtesCatalogo() {
	}

	static List<MartialArt> artes() {
		return List.of(
				judo(),
				karate(),
				taekwondo(),
				jiuJitsu(),
				muayThai(),
				boxe());
	}

	private static MartialArt judo() {
		return new MartialArt(
				"judo",
				"Judo",
				"Judo",
				"Japão",
				1882,
				Disciplina.THROWING,
				"Arte das projeções: usa o peso e o equilíbrio do oponente contra "
						+ "ele mesmo para derrubar e controlar.",
				"Jigoro Kano fundou o judo em 1882 no Kodokan, em Tóquio. Ele reuniu "
						+ "técnicas do jujutsu e do tenjin shinho-ryu, descartando o que "
						+ "dependia de força bruta. A ideia central é o princípio da "
						+ "eficiência (jusei-gokyu): usar a menor quantidade de força "
						+ "necessária para vencer. O judo entrou nos Jogos Olímpicos em 1964 "
						+ "e hoje é praticado em mais de 150 países.",
				List.of(
						new Destaque("Eficiente sem machucar",
								"Termina a maioria dos confrontos com uma projeção controlada, "
										+ "sem golpes que causem dano permanente."),
						new Destaque("Ensina postura e controle",
								"O foco em base e equilibrio desenvolve disciplina corporal "
										+ "e consciência corporal no dia a dia."),
						new Destaque("Aberto a mulheres e crianças",
								"O protocolo do Kodokan permite treino misto desde a fundação."),
						new Destaque("Ferramenta de autoconhecimento",
								"Ensina a cair com segurança, o que reduz o risco de lesão "
										+ "em qualquer atividade física.")),
				List.of(
						new Destaque("Exige muitos anos de prática",
								"As técnicas só ficam automáticas depois de longa repetição "
										+ "com um parceiro de treino."),
						new Destaque("Não ensina golpes de mão",
								"Quem domina só projeções fica limitado se o adversário "
										+ "mantiver a distância."),
						new Destaque("Pouca defesa no chão",
								"Sem trabalho de submisso, o judoca tem dificuldade "
										+ "contra quem sabe se manter embaixo."),
						new Destaque("Treino sempre em dupla",
								"Projeções não podem ser estudadas sozinho, ao contrário "
										+ "do que ocorre com técnicas de golpe.")),
				"🤼",
				"f44JZo3Vkuk");
	}

	private static MartialArt karate() {
		return new MartialArt(
				"karate",
				"Karate",
				"Karate",
				"Okinawa, Japão",
				1930,
				Disciplina.STRIKING,
				"Arte de golpes diretos de mão e perna, com base sólida e "
						+ "movimentos curtos e rápidos.",
				"O karate nasceu em Okinawa, ilha onde a luta com armas era comum "
						+ "e foi proibida pelas autoridades japonesas. Os aldeões "
						+ "adaptaram o kempo (uso de armas) ao trabalho manual, "
						+ "transformando golpes em ferramentas. Em 1920, o mestre "
						+ "Gichin Funakoshi mudou o nome de Te (mão da China) para "
						+ "kara (vazio), altera o significado para o filosófico. "
						+ "Em 1933, o mestre foi invitado ao Japan Budokan e Fundou "
						+ "o Shotokan, que se tornou o estilo mais praticado no mundo.",
				List.of(
						new Destaque("Golpes diretos e rápidos",
								"Técnicas lineares de mão e perna economizam tempo e "
										+ "alcançam longas distâncias."),
						new Destaque("Base e postura sólidas",
								"O trabalho de pernas e quadril dá uma firmeza "
										+ "que sustenta o corpo nos golpes."),
						new Destaque("Acessível e barato",
								"Precisa de pouco espaço e equipamento, o que facilita "
										+ "a inclusão em escolas e comunidades.")),
				List.of(
						new Destaque("Alcance curto",
								"Depende de se aproximar do oponente, momento em que "
										+ "o adversário também ataca."),
						new Destaque("Pouca resposta no chão",
								"Não há trabalho de solo, que hoje é essencial "
										+ "em qualquer modalidade de combate."),
						new Destaque("Treino pode machucar",
								"A repetição excessiva causa lesões em joelhos e "
										+ "cotovelos se não houver controle.")),
				"👊",
				"DQnhwpTVVaw");
	}

	private static MartialArt taekwondo() {
		return new MartialArt(
				"taekwondo",
				"Taekwondo",
				"Taekwondo",
				"Coreia do Sul",
				1944,
				Disciplina.STRIKING,
				"Arte de chutes com alta velocidade e potência, apoiada em "
						+ "técnicas de respiração e controle de distância.",
				"O taekwondo surgiu na Coreia em 1944, quando o general Choi "
						+ "Hong-hi uniu estilos de punho e perna praticados no "
						+ "Exército. O nome junta as três palavras: tae (pé), kwon "
						+ "(mão) e do (caminho). Em 1972, a associação Coreana criou a World "
						+ "Taekwondo Federation, hoje a maior organização do esporte. "
						+ "Entrou nos Jogos Olímpicos em 1988 e virou item oficial "
						+ "em 2000, com destaque para os chutes circulares.",
				List.of(
						new Destaque("Chutes de grande alcance",
								"A perna é a arma mais longa do corpo, o que permite "
										+ "atacar mantendo-se longe do oponente."),
						new Destaque("Velocidade e resistência",
								"O treino de milhares de repetições aumenta a velocidade "
										+ "do chute e a capacidade aeróbica."),
						new Destaque("Disciplina e concentração",
								"A respiração em kihap e o controle da postura "
										+ "melhoram muito o foco e o autocontrole.")),
				List.of(
						new Destaque("Vulnerável no chão",
								"Praticar contra quem tem experiência no solo é "
										+ "exposto, pois quase não há defesa para quem cai."),
						new Destaque("Exige flexibilidade alta",
								"Sem alongamento diário, os chutes altos ficam "
										+ "limitados e há risco de lesão."),
						new Destaque("Punho e perna são fracos",
								"Muitos estilos reduziram o treino "
										+ "de mãos, enfraquecendo o combate de perto.")),
				"🦵",
				"MWgsWRn4JXI");
	}

	private static MartialArt jiuJitsu() {
		return new MartialArt(
				"jiu-jitsu",
				"Brazilian Jiu-Jitsu",
				"Jiu-Jitsu",
				"Brasil",
				1914,
				Disciplina.GRAPPLING,
				"Arte de controle no chão que usa alavancas e ângulos para "
						+ "vencer oponentes maiores sem depender de força.",
				"O jiu-jitsu brasileiro nasceu no início do século XX, quando os "
						+ "mestres de judo chegaram ao Brasil. Mitsuyo Maeda ensinou a "
						+ "família Gracie no Rio de Janeiro, que adaptou a arte ao solo. "
						+ "Em 1950, a federação brasileira reconheceu o jiu-jitsu como "
						+ "arte nacional. Com a chegada do jiu-jitsu internacional na década "
						+ "de 1990, a tecnica se espalhou e virou base do MMA, "
						+ "tornando o submit a linguagem dominante do esporte.",
				List.of(
						new Destaque("Vantagem técnica sobre força",
								"Alavancas e ângulos permitem que um praticante menor "
										+ "vença um oponente maior."),
						new Destaque("Controle do chão",
								"Quem domina as posições controla o ritmo e "
										+ "impede o adversário de se levantar."),
						new Destaque("Finalizações precisas",
								"Estrangulamentos e bloqueio de articulação encerram o "
										+ "confronto com muita eficiência."),
						new Destaque("Base para o MMA",
								"Praticado hoje pela maioria dos lutadores de "
										+ "combate profissional no mundo.")),
				List.of(
						new Destaque("Demorar muito para virar útil",
								"Quem treina precisa de pelo menos um ano para conseguir "
										+ "escapar de uma situação comum no chão."),
						new Destaque("Falta de golpes em pé",
								"A arte não ensina a defender de um soco ou chute "
										+ "antes de ir para o solo."),
						new Destaque("Risco de lesão em articulações",
								"Sem cuidado com o joelho e o cotovelo, algumas "
										+ "técnicas causam lesões graves."),
						new Destaque("Costuma deixar marcas",
								"O treino apertado e as raspagens causam "
										+ "irritação constante na pele.")),
				"🥋",
				"r8XXMhSblFI");
	}

	private static MartialArt muayThai() {
		return new MartialArt(
				"muay-thai",
				"Muay Thai",
				"Muay Thai",
				"Tailândia",
				1553,
				Disciplina.STRIKING,
				"Conhecida como a arte das oito partes do corpo, usa punhos, "
						+ "pernas, joelhos e cotovelos, além de luta clinica.",
				"O Muay Thai tem raízes no Muay Boran, praticado nos campos de "
						+ "batalha do antigo reino siamês. Em 1553, o rei Maha Chakkraphat "
						+ "promoveu o primeiro combate público de muay, apresentado diante "
						+ "da corte. Durante o século XX, com a modernização do país, as "
						+ "regras foram padronizadas e o treino ganhou regulamentação. "
						+ "Hoje é a base do striking no MMA, e grande parte dos lutadores "
						+ "começa pela prática de muay thai.",
				List.of(
						new Destaque("Usa todo o corpo",
								"Chutes, joelhos e cotovelos ampliam as opções de golpe "
										+ "e dificultam a leitura do adversário."),
						new Destaque("Condicionamento excelente",
								"Rounds intensos de cinco minutos exigem uma preparação "
										+ "aeróbica fora do comum."),
						new Destaque("Luta clínica",
								"O trabalho de corpo a corpo ensina a controlar a "
										+ "distância e a derrubar com segurança."),
						new Destaque("Golpes de alto impacto",
								"Joelhos na coxa e cotovelos na cabeça causam dano "
										+ "significativo mesmo com defesa.")),
				List.of(
						new Destaque("Golpes doem muito",
								"Joelhos e cotovelos causam lesões graves se "
										+ "atingirem o olho ou a coluna."),
						new Destaque("Pouco trabalho no chão",
								"A falta de defesa de queda leva a lutas "
										+ "desfavoráveis para quem vai para o solo."),
						new Destaque("Treino muito duro",
								"As intensidades do treino exigem amortecedores, "
										+ "controle médico e descanso entre as sessões.")),
				"🥊",
				"ByA7F49Y0cE");
	}

	private static MartialArt boxe() {
		return new MartialArt(
				"boxe",
				"Boxe",
				"Boxing",
				"Inglaterra",
				1867,
				Disciplina.STRIKING,
				"Esporte de combate que combina socos, movimentacao e estrategia, "
						+ "com regras que variam conforme a modalidade.",
				"Combates de punho existem desde a Antiguidade. O boxe moderno tomou "
						+ "forma na Inglaterra, e as regras de Queensberry, publicadas em "
						+ "1867, ajudaram a padronizar o uso de luvas e os assaltos. "
						+ "A modalidade se desenvolveu como esporte com categorias de peso, "
						+ "arbitragem e regras para proteger os competidores. No cinema, "
						+ "Sylvester Stallone criou e interpretou Rocky Balboa, personagem "
						+ "fictício; Stallone é ator e roteirista, não boxeador profissional.",
				List.of(
						new Destaque("Condicionamento e coordenação",
								"Exercícios de técnica e movimentação podem trabalhar "
										+ "resistência, equilíbrio e coordenação."),
						new Destaque("Foco e disciplina",
								"Aprender combinações e controlar a distância exige "
										+ "atenção, repetição e paciência."),
						new Destaque("Treinos variados",
								"É possível praticar fundamentos e condicionamento sem "
										+ "fazer sparring ou competir.")),
				List.of(
						new Destaque("Risco de impacto",
								"O contato pode causar lesões, especialmente na cabeça "
										+ "e nas mãos; sparring exige instrução e proteção."),
						new Destaque("Não cobre todas as distâncias",
								"O boxe esportivo se concentra em socos e não ensina "
										+ "chutes, projeções ou luta no chão."),
						new Destaque("Exige progressão segura",
								"Treinar com intensidade ou contato antes de dominar "
										+ "os fundamentos aumenta o risco de lesões.")),
				"🥊",
				"kKDHdsZn8RY");
	}
}
