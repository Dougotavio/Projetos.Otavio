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
				"Japao",
				1882,
				Disciplina.THROWING,
				"Arte das projecoes: usa o peso e o equilibrio do oponente contra "
						+ "ele mesmo para derrubar e controlar.",
				"Jigoro Kano fundou o judo em 1882 no Kodokan, em Tokio. Ele reuniu "
						+ "tecnicas do jujutsu e do tenjin shinho-ryu, descartando o que "
						+ "dependia de forca bruta. A ideia central e o principio da "
						+ "eficiencia (jusei-gokyu): usar a menor quantidade de forca "
						+ "necessaria para vencer. O judo entrou nos Jogos Olimpicos em 1964 "
						+ "e hoje e praticado em mais de 150 paises.",
				List.of(
						new Destaque("Eficiente sem machucar",
								"Termina a maioria dos confrontos com uma projecao controlada, "
										+ "sem golpes que causem dano permanente."),
						new Destaque("Ensina postura e controle",
								"O foco em base e equilibrio desenvolve disciplina corporal "
										+ "e consciencia corporal no dia a dia."),
						new Destaque("Aberto a mulheres e criancas",
								"O protocolo do Kodokan permite treino misto desde a fundacao."),
						new Destaque("Ferramenta de autoconhecimento",
								"Ensina a cair com seguranca, o que reduz o risco de lesao "
										+ "em qualquer atividade fisica.")),
				List.of(
						new Destaque("Exige muitos anos de pratica",
								"As tecnicas so ficam automaticas depois de longa repeticao "
										+ "com um parceiro de treino."),
						new Destaque("Nao ensina golpes de mao",
								"Quem domina so projecoes fica limitado se o adversario "
										+ "mantiver a distancia."),
						new Destaque("Pouca defesa no chao",
								"Sem trabalho de submisso, o judoca tem dificuldade "
										+ "contra quem sabe se manter embaixo."),
						new Destaque("Treino sempre em dupla",
								"Projecoes nao podem ser estudadas sozinho, ao contrario "
										+ "do que ocorre com tecnicas de golpe.")),
				"🤼",
				"f44JZo3Vkuk");
	}

	private static MartialArt karate() {
		return new MartialArt(
				"karate",
				"Karate",
				"Karate",
				"Okinawa, Japao",
				1930,
				Disciplina.STRIKING,
				"Arte de golpes diretos de mao e perna, com base solida e "
						+ "movimentos curtos e rapidos.",
				"O karate nasceu em Okinawa, ilha onde a luta com armas era comum "
						+ "e foi proibida pelas autoridades japonesas. Os aldeoes "
						+ "adaptaram o kempo (uso de armas) ao trabalho manual, "
						+ "transformando golpes em ferramentas. Em 1920, o mestre "
						+ "Gichin Funakoshi mudou o nome de Te (mao da China) para "
						+ "kara (vazio), altera o significado para o filosofico. "
						+ "Em 1933, o mestre foi invitado ao Japan Budokan e Fundou "
						+ "o Shotokan, que se tornou o estilo mais praticado no mundo.",
				List.of(
						new Destaque("Golpes diretos e rapidos",
								"Tecnicas lineares de mao e perna economizam tempo e "
										+ "alcancam longas distancias."),
						new Destaque("Base e postura solidas",
								"O trabalho de pernas e quadril da uma firmeza "
										+ "que sustenta o corpo nos golpes."),
						new Destaque("Acessivel e barato",
								"Precisa de pouco espaco e equipamento, o que facilita "
										+ "a inclusao em escolas e comunidades.")),
				List.of(
						new Destaque("Alcance curto",
								"Depende de se aproximar do oponente, momento em que "
										+ "o adversario tambem ataca."),
						new Destaque("Pouca resposta no chao",
								"Nao ha trabalho de solo, que hoje e essencial "
										+ "em qualquer modalidade de combate."),
						new Destaque("Treino pode machucar",
								"A repeticao excessiva causa lesoes em joelhos e "
										+ "cotovelos se nao houver controle.")),
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
				"Arte de chutes com alta velocidade e potencia, apoiada em "
						+ "tecnicas de respiracao e controle de distancia.",
				"O taekwondo surgiu na Coreia em 1944, quando o general Choi "
						+ "Hong-hi uniu estilos de punho e perna praticados no "
						+ "Exercito. O nome junta as tres palavras: tae (pe), kwon "
						+ "(mao) e do (caminho). Em 1972, a associacao Coreana criou a World "
						+ "Taekwondo Federation, hoje a maior organizacao do esporte. "
						+ "Entrou nos Jogos Olimpicos em 1988 e virou Item Oficial "
						+ "em 2000, com destaque para os chutes circulares.",
				List.of(
						new Destaque("Chutes de grande alcance",
								"A perna e a arma mais longa do corpo, o que permite "
										+ "atacar mantendo-se longe do oponente."),
						new Destaque("Velocidade e resistencia",
								"O treino de miles de repeticoes aumenta a velocidade "
										+ "do chute e a capacidade aerobica."),
						new Destaque("Disciplina e concentracao",
								"A respiracao em kihap e o controle da postura "
										+ "melhoram muito o foco e a autocontrole.")),
				List.of(
						new Destaque("Vulneravel no chao",
								"Praticar contra quem tem experiencia em solo e "
										+ "exposto, pois quase nao ha defesa para quem cai."),
						new Destaque("Exige flexibilidade alta",
								"Sem alongamento diario, os chutes altos ficam "
										+ "limitados e ha risco de lesao."),
						new Destaque("Punho e perna sao fracos",
								"Muitos estilos reduziram o treino "
										+ "de maos, enfraquecendo o combate de perto.")),
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
				"Arte de controle no chao que usa alavancas e angulos para "
						+ "vencer oponentes maiores sem depender de forca.",
				"O jiu-jitsu brasileiro nasceu no inicio do seculo XX, quando os "
						+ "mestres de judo chegaram ao Brasil. Mitsuyo Maeda ensinou a "
						+ "familia Gracie no Rio de Janeiro, que adaptou a arte ao solo. "
						+ "Em 1950, a federacao brasileira reconheceu a jiu-jitsu como "
						+ "arte nacional. Com a chegada do jiu-jitsu internacional na decada "
						+ "de 1990, a tecnica se espalhou e virou base do MMA, "
						+ "tornando o submit a linguagem dominante do esporte.",
				List.of(
						new Destaque("Vantagem tecnica sobre forca",
								"Alavancas e angulos permitem que um praticante menor "
										+ "venca um oponente maior."),
						new Destaque("Controle do chao",
								"Quem domina as posicoes controla o ritmo e "
										+ "impede o adversario de se levantar."),
						new Destaque("Finalizacoes precisas",
								"Estrangulamentos e bloqueio de articulo encerram o "
										+ "confronto com muita eficiencia."),
						new Destaque("Base para o MMA",
								"Praticado hoje pela maioria dos lutadores de "
										+ "combate profissional no mundo.")),
				List.of(
						new Destaque("Demorar muito para virar util",
								"Quem Treina precisa de pelo menos um ano para conseguir "
										+ "escapar de uma situacao comum no chao."),
						new Destaque("Falta de golpes em pe",
								"A arte nao ensina a defender de um soco ou chute "
										+ "antes de ir para o solo."),
						new Destaque("Risco de lesao em articulacoes",
								"Sem cuidado com o joelho e o cotovelo, algumas "
										+ "tecnicas causam lesoes graves."),
						new Destaque("Costuma deixar marcas",
								"O treino apertado e as raspagens causam "
										+ "irritacao constante na pele.")),
				"🥋",
				"r8XXMhSblFI");
	}

	private static MartialArt muayThai() {
		return new MartialArt(
				"muay-thai",
				"Muay Thai",
				"Muay Thai",
				"Tailandia",
				1553,
				Disciplina.STRIKING,
				"Conhecida como a arte das oito partes do corpo, usa punhos, "
						+ "pernas, joelhos e cotovelos, alem de luta clinica.",
				"O Muay Thai tem raizes no Muay Boran, praticado nos campos de "
						+ "batalha do antigo reino siames. Em 1553, o rei Maha Chakkraphat "
						+ "promoveu o primeiro combate publico de muay, apresentado diante "
						+ "da corte. Durante o seculo XX, com a modernizacao do pais, as "
						+ "regras foram padronizadas e o treino ganhou regulamentacao. "
						+ "Hoje e a base do striking no MMA, e grande parte dos lutadores "
						+ "comeca pela pratica de muay thai.",
				List.of(
						new Destaque("Usa todo o corpo",
								"Chutes, joelhos e cotovelos ampliam as opcoes de golpe "
										+ "e dificultam a leitura do adversario."),
						new Destaque("Condicionamento excelente",
								"Rounds intensos de cinco minutos exigem uma preparacao "
										+ "aerobica fora do comum."),
						new Destaque("Luta clinica",
								"O trabalho de corpo a corpo ensina a controlar a "
										+ "distancia e a derrubar com seguranca."),
						new Destaque("Golpes de alto impacto",
								"Joelhos na coxa e cotovelos na cabeca causam dano "
										+ "significativo mesmo com defesa.")),
				List.of(
						new Destaque("Golpes doem muito",
								"Joelhos e cotovelos causam injurias graves se "
										+ "atingirem o olho ou a coluna."),
						new Destaque("Pouco trabalho no chao",
								"A falta de defesa de queda leva a lutas "
										+ "desfavoraveis para quem vai para o solo."),
						new Destaque("Treino muito duro",
								"As intensidades do treino exigem amortecedores, "
										+ "controle medico e descanso entre as sessoes.")),
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
						+ "ficticio; Stallone e ator e roteirista, nao boxeador profissional.",
				List.of(
						new Destaque("Condicionamento e coordenacao",
								"Exercicios de tecnica e movimentacao podem trabalhar "
										+ "resistencia, equilibrio e coordenacao."),
						new Destaque("Foco e disciplina",
								"Aprender combinacoes e controlar a distancia exige "
										+ "atencao, repeticao e paciencia."),
						new Destaque("Treinos variados",
								"E possivel praticar fundamentos e condicionamento sem "
										+ "fazer sparring ou competir.")),
				List.of(
						new Destaque("Risco de impacto",
								"O contato pode causar lesoes, especialmente na cabeca "
										+ "e nas maos; sparring exige instrucao e protecao."),
						new Destaque("Nao cobre todas as distancias",
								"O boxe esportivo se concentra em socos e nao ensina "
										+ "chutes, projecoes ou luta no chao."),
						new Destaque("Exige progressao segura",
								"Treinar com intensidade ou contato antes de dominar "
										+ "os fundamentos aumenta o risco de lesoes.")),
				"🥊",
				"kKDHdsZn8RY");
	}
}
