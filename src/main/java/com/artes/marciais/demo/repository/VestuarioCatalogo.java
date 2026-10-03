package com.artes.marciais.demo.repository;

import java.util.List;

import com.artes.marciais.demo.model.Destaque;
import com.artes.marciais.demo.model.Peca;
import com.artes.marciais.demo.model.Vestuario;

/**
 * Dados de exemplo dos trajes das artes marciais do site.
 *
 * O conteudo fica hard-coded de proposito, como em {@link ArtesCatalogo}: o
 * foco do site e a apresentacao do material, e nao a persistencia. O texto
 * evita acentos, em acordo com os demais arquivos do projeto.
 */
final class VestuarioCatalogo {

	private VestuarioCatalogo() {
	}

	static List<Vestuario> vestuarios() {
		return List.of(
				judo(),
				karate(),
				taekwondo(),
				jiuJitsu(),
				muayThai(),
				boxe());
	}

	private static Vestuario judo() {
		return new Vestuario(
				"judo",
				"Judogi",
				"Judo-gi",
				"Uniforme de corte rigido, feito para aguentar projecoes no tatame "
						+ "e nao abrir no encaixe das pecas.",
				"Algodao pesado ou mistura de algodao e poliester, de 10 a 12 oz. "
						+ "O judo de elite usa tecidos de 12 oz ou mais.",
				List.of(
						"Azul: a cor padrao da maioria das escolas",
						"Branco: exigido em varias competicoes e em aulas avancadas",
						"Uniforme branco com azul apenas nas costas e nas mangas, "
								+ "usado por algumas federacoes"),
				List.of(
						new Peca("Uke (jaqueta)",
								"Parte superior de colarinho direito, com costura de reforco "
										+ "sobre o ombro oposto ao braco que prende o traje."),
						new Peca("Hakama (calca)",
								"Calca de oito a doze panos e mais larga nas pernas, para "
										+ "permitir movimentos amplos de projecao."),
						new Peca("Obi (faixa)",
								"Faixa que amarra o traje; a cor indica o nivel de "
										+ "graduacao e identifica o praticante no tatame."),
						new Peca("Etiquetagem",
								"Coloque seu nome no traje. A numeracao usada em exame e a "
										+ "etiqueta de nome sao coisas distintas, e cada escola "
										+ "define a sua regra.")),
				List.of(
						"Branco - iniciante",
						"Amarelo ou ciano - 1o kyu",
						"Laranja - 2o kyu",
						"Verde - 3o kyu",
						"Azul - 4o kyu",
						"Roxo - 5o kyu",
						"Marrom - ajuste antes do preto",
						"Preto - 1o dan ou acima"),
				"Descalco no tatame. Para trabalho de queda, o ukemi e a tecnica de "
						+ "amortecimento: meias e canos de neve nao substituem o tatame.",
				List.of(
						new Destaque("Lave depois de cada treino",
								"Suor e terceiro no tecido criam odor rapido; lave o judogi "
										+ "separado de roupas claras e de toalhas."),
						new Destaque("Seque a sombra e are",
								"O corte entre um treino e outro mantem o tecido poroso e "
										+ "reduz o cheiro sem necessidade de produto forte."),
						new Destaque("Lave com cuidado",
								"O tecido pesado perde forma com agua quente; use ciclo "
										+ "suave, agua fria e nao use alvejante."),
						new Destaque("Compre pensando no crescimento",
								"No judo o traje e usado em tecnicas de quebra de pegada, "
										+ "entao costuma render mais que a media: verifique "
										+ "a medida da calca com o instrutor.")),
				"https://commons.wikimedia.org/wiki/Special:FilePath/Judogi_Azul.jpg?width=640",
				"https://commons.wikimedia.org/wiki/File:Judogi_Azul.jpg",
				"Luishernando - CC BY-SA 3.0",
				"judogi+judo+kimono");
	}

	private static Vestuario karate() {
		return new Vestuario(
				"karate",
				"Karategi",
				"Karategi",
				"Kimono leve e solto, sem colarinho, pensado para treino em sala "
						+ "quente e para aulas com muito deslocamento e chute.",
				"Algodao ou mistura leve, de 4 a 8 oz, bem mais fino que o judogi.",
				List.of(
						"Branco: a cor exigida em competicao pela WKF",
						"Estilo escolar e de caracterizacao aceitam uniformes coloridos",
						"Cinza: nivel avancado em varias federacoes"),
				List.of(
						new Peca("Uke (jaqueta)",
								"Parte superior em V, sem gola, com menos pano que a do "
										+ "judogi para nao limitar o ombro no chute."),
						new Peca("Hakama (calca)",
								"Calca reta e folgada, quase sempre sem a amarra larga do "
										+ "judogi."),
						new Peca("Obi (faixa)",
								"Faixa simples de algodao que prende o traje; a cor indica "
										+ "o nivel e o tipo de exame."),
						new Peca("Kanji (bordado)",
								"Caligrafia bordada no peito e nas costas, com o nome da "
										+ "arte ou da escola.")),
				List.of(
						"Branco - iniciante",
						"Amarelo - 9o kyu",
						"Laranja - 8o kyu",
						"Verde - 7o kyu",
						"Azul - 6o kyu",
						"Roxo - 5o kyu",
						"Marrom - 3o kyu",
						"Preto - shodan, exige exame"),
				"Descalco no tatame. Base e medida do chute ficam mais fieis em pe "
						+ "descalco, porque o pe toca o chao em todos os momentos.",
				List.of(
						new Destaque("Lave em ciclo curto",
								"Sendo leve, o karategi costuma aceitar maquina com agua "
										+ "fria; separe de roupas escuras nas primeiras lavagens."),
						new Destaque("Dobra ainda umido",
								"O karategi amassa com facilidade e absorve muita umidade; "
										+ "a dobra feita logo apos o banho dura mais."),
						new Destaque("Cuidado com o bordado",
								"O kanji e a marca do tecido amarelam com lavagem a quente "
										+ "ou secagem ao sol.")),
				"https://commons.wikimedia.org/wiki/Special:FilePath/Cuidado-kara%20te%20gi.gif?width=640",
				"https://commons.wikimedia.org/wiki/File:Cuidado-kara_te_gi.gif",
				"Marloneescobarc - CC BY-SA 4.0",
				"karategi+kimono+karate");
	}

	private static Vestuario taekwondo() {
		return new Vestuario(
				"taekwondo",
				"Dobok",
				"Dobok",
				"Uniforme branco de corte coreano, com gola em V e bordas coloridas "
						+ "que mudam com o nivel; a calca e larga para o chute.",
				"Algodao, microfibra ou naylon, bem mais fino que o judogi para "
						+ "permitir a rotacao rapida do quadril no giro do chute.",
				List.of(
						"Branco: a cor base do dobok em quase todas as federacoes",
						"Kaldari: bordas e gola coloridas indicam o nivel",
						"Azul ou preto: uniforme escuro usado por algumas federacoes "
								+ "e no taekwondo profissional"),
				List.of(
						new Peca("Jaqueta",
								"Abertura frontal em V, sem gola, e bordas coloridas que "
										+ "identificam o nivel do praticante."),
						new Peca("Calca",
								"Corte largo, com abertura lateral na barra e mais panos nas "
										+ "pernas que a do karate."),
						new Peca("Cinto (obi)",
								"Cinto simples atado na frente ou atras; a cor indica o nivel "
										+ "e o estilo do no muda conforme a federacao."),
						new Peca("Meias",
								"Sao obrigatorias na execucao de Poomsae em varias "
										+ "competicoes da World Taekwondo."),
						new Peca("Caneleira",
								"Protege a canela no treino de chute; existe a versao "
										+ "acolchoada usada nas provas de chute.")),
				List.of(
						"Branco - 10o gup",
						"Amarelo - 9o gup",
						"Verde - 8o gup",
						"Azul - 7o gup",
						"Vermelho - 6o gup",
						"Preto - 1o dan",
						"Preto com listra colorida - dan intermediario"),
				"Descalco no tatame. O dorso do pe toca o chao no instante do chute, "
						+ "e por isso o treino descalco faz parte da tecnica.",
				List.of(
						new Destaque("O tecido amassa",
								"O dobok marca com facilidade; dobre ainda umido e "
										+ "seque a sombra para a dobra durar."),
						new Destaque("Bordas coloridas desbotam",
								"Lave o avesso e separado para preservar o kaldari, que e "
										+ "a parte que identifica o nivel."),
						new Destaque("Seque a caneleira",
								"Depois do treino de chute, seque a caneleira por completo: "
										+ "espuma umida e a causa mais comum de odor.")),
				"https://commons.wikimedia.org/wiki/Special:FilePath/BlackBeltDobokWTF.jpg?width=640",
				"https://commons.wikimedia.org/wiki/File:BlackBeltDobokWTF.jpg",
				"Thedarshan - CC BY-SA 3.0",
				"dobok+taekwondo");
	}

	private static Vestuario jiuJitsu() {
		return new Vestuario(
				"jiu-jitsu",
				"Gi",
				"Gi",
				"Kimono de algodao com faixa de cintura. A versao No-Gi dispensa o "
						+ "kimono e usa regata e short com elastico.",
				"Algodao cru ou pre-lavado. O cru pesa mais e encolhe na primeira "
						+ "lavagem; o pre-lavado ja vem no tamanho final.",
				List.of(
						"Branco: o mais comum, e mostra o desgaste com o tempo de treino",
						"Preto, azul e cinza: alternativas mais discretas",
						"Em competicao, a faixa costuma vir com remendo colorido para o "
								+ "juiz identificar o nivel"),
				List.of(
						new Peca("Kimono",
								"Jaqueta de lapela larga (gorilla) e calca com reforco nos "
										+ "joelhos; a lapela e a peca que mais rasga."),
						new Peca("Faixa (belt)",
								"Fita de algodao trancado ou costurado; a cor e a posicao na "
										+ "perna e no braco indicam o nivel."),
						new Peca("Regata (rashguard)",
								"Camiseta de poliamida usada por baixo do kimono e no No-Gi, "
										+ "para suar frio."),
						new Peca("Protetor bucal",
								"Obrigatorio no treino e na competicao; escolha um que voce "
										+ "consiga manter fechado."),
						new Peca("Meia de silicone",
								"Melhora a aderencia do pe no tatame; em piso sujo, a meia "
										+ "comum e mais segura.")),
				List.of(
						"Branco - iniciante",
						"Azul",
						"Roxo",
						"Marrom",
						"Preto com tarja branca",
						"Preto com tarja vermelha",
						"Criancas: cinza, amarelo, laranja, verde e azul"),
				"Descalco no tatame. Meia de silicone e usada quando o piso esta "
						+ "limpo e seco, e nao substitua o calcado em piso sujo.",
				List.of(
						new Destaque("Algodao cru encolhe",
								"Compre um tamanho acima ou opte pelo pre-lavado, senao o gi "
										+ "fica curto depois da primeira lavagem."),
						new Destaque("Lave do avesso",
								"O desgaste vem do atrito entre as pecas; agua fria em rede "
										+ "e sem amaciante, que gruda no tecido."),
						new Destaque("Reforce a barra",
								"Um ponto de zigue-zague na barra evita o desfiamento, que e "
										+ "a parte do gi que mais se estraga."),
						new Destaque("Lave o kimono separado",
								"Quem treina No-Gi usa a mesma regata; separe as roupas de "
										+ "treino com e sem kimono.")),
				"https://commons.wikimedia.org/wiki/Special:FilePath/BJJ%2C%20brazilian-jiujitsu%2003.jpg?width=640",
				"https://commons.wikimedia.org/wiki/File:BJJ,_brazilian-jiujitsu_03.jpg",
				"Yossigur - CC BY-SA 4.0",
				"kimono+jiu-jitsu+gi");
	}

	private static Vestuario muayThai() {
		return new Vestuario(
				"muay-thai",
				"Shorts e itens de treino",
				"Shorts de Muay Thai",
				"O Muay Thai nao tem uniforme obrigatorio: o traje vai de shorts de "
						+ "treino e itens de protecao ate os acessorios cerimoniais.",
				"Shorts de poliester ou spandex, leves e com elasticidade para "
						+ "chute, joelhada e clinche.",
				List.of(
						"Sem cor oficial definida pela modalidade",
						"Muitos studios usam a cor do time ou uma faixa colorida no shorts",
						"Mongkhon e prajiat sao acessorios cerimoniais, de cor variavel"),
				List.of(
						new Peca("Shorts",
								"O item de uso diario; o cos costuma ser elastico e largo o "
										+ "bastante para o chute na coxa."),
						new Peca("Bandagem de maos",
								"Faixa de tecido ou elastico enrolada sob a luva, que protege "
										+ "o punho e as falanges."),
						new Peca("Protetor bucal",
								"Obrigatorio em clinche e sparring, onde o contato no rosto "
										+ "e comum."),
						new Peca("Caneleira",
								"Protege a canela no treino de chute e no clinche."),
						new Peca("Mongkhon",
								"Aro de tecido usado na entrada cerimonial e em rituais, "
										+ "herda da tradicao."),
						new Peca("Prajiat",
								"Faixa que cruza o peito, da tradicao Muay Boran e raramente "
										+ "exigida no treino moderno.")),
				List.of(
						"Nao ha faixa de graduacao padronizada",
						"Muitas academias usam faixas ou cordoes coloridos definidos pelo "
								+ "treinador",
						"O nivel real aparece no clinche, na tecnica e no condicionamento"),
				"Descalco no tatame. Muitas aulas comecam e terminam com alongamento "
						+ "e trabalho de canelas.",
				List.of(
						new Destaque("Troque a bandagem na hora certa",
								"Bandagem suja ou fora de alinhamento forca a articulacao; "
										+ "troque quando perder elasticidade."),
						new Destaque("Protetor bucal em estojo",
								"Enxague e seque por fora: umidade fechada e o ambiente ideal "
										+ "para bacteria."),
						new Destaque("Poliester seca rapido",
								"O shorts seca em minutos; evite lavagem a quente para nao "
										+ "perder a elasticidade.")),
				"https://commons.wikimedia.org/wiki/Special:FilePath/Muay_Thai_shorts.jpg?width=640",
				"https://commons.wikimedia.org/wiki/File:Muay_Thai_shorts.jpg",
				"muay-siam.com - dominio publico",
				"shorts+muay+thai");
	}

	private static Vestuario boxe() {
		return new Vestuario(
				"boxe",
				"Ring attire",
				"Boxing attire",
				"O boxe nao usa kimono: trunks, singlet em algumas federacoes, robe "
						+ "para a entrada, mais meias, tenis e protecoes.",
				"Trunks de poliester leve com cos confortavel; o robe costuma ser de "
						+ "cetim ou poliester.",
				List.of(
						"Os trunks nao tem cor padronizada: o time e o evento escolhem",
						"Laterais ou faixas coloridas ajudam a distinguir o lutador a "
								+ "distancia",
						"Em varias federacoes, o singlet vem em vermelho ou azul para "
								+ "separar os lados do combate"),
				List.of(
						new Peca("Trunks",
								"Calcao curto com cos mais alto nas laterais; e o item "
								+ "obrigatorio no ringue."),
						new Peca("Robe",
								"Kimono leve usado na entrada e na saida; vermelho e verde "
								+ "sao os mais comuns."),
						new Peca("Singlet (camiseta)",
								"Camiseta sem manga exigida em varias competicoes de "
								+ "amadores e nem sempre usada no profissional."),
						new Peca("Bandagem de maos",
								"Enrolada sob a luva, apoia o punho e reduz o risco de "
								+ "lesao."),
						new Peca("Protetor bucal",
								"Obrigatorio em qualquer treino de contato."),
						new Peca("Tenis de boxe",
								"Com solado de borracha e suporte no tornozelo; a amarracao "
								+ "pode passar por cima do tornozelo.")),
				List.of(
						"Nao existe faixa de graduacao no boxe",
						"Nos amadores, a classificacao e por faixa etaria e peso",
						"A faixa de nivel (iniciante, intermediario, avancado) aparece "
								+ "na inscricao, nao no traje"),
				"Tenis proprio de boxe, com amarracao firme. Nao treine descalco nem "
						+ "de meias: a base depende do contato do pe com o solo.",
				List.of(
						new Destaque("Troque de roupa depois do treino",
								"Roupa encharcada de suor mantem o odor e irrita a pele; "
										+ "leve uma segunda e vista antes de sair."),
						new Destaque("Protecao e de uso pessoal",
								"Enxague e seque protetor bucal e bandagens: item "
										+ "compartilhado e via de contaminacao."),
						new Destaque("Luvas e bandagem combinam",
								"Bandagem frouxa ou do tamanho errado faz o punao "
										+ "escorregar dentro da luva em cada golpe.")),
				"https://commons.wikimedia.org/wiki/Special:FilePath/Bobby_Fuller%2C_boxer%2C_ca.%201945_-_photograph_by_Phil_Ward_%284328871377%29.jpg?width=640",
				"https://commons.wikimedia.org/wiki/File:Bobby_Fuller,_boxer,_ca._1945_-_photograph_by_Phil_Ward_(4328871377).jpg",
				"Phil Ward / State Library of NSW - sem restricoes conhecidas",
				"shorts+boxe+trunks");
	}
}