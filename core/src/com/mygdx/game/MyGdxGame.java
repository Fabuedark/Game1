package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameScreen gameScreen;
	private MainMenu mainMenu;
	public static boolean gameActive;

//!!!
	//ПЕРЕПИСАТЬ КОД НА ООП
	// карточки бафов сделать кнопками, сделать их отдельным классом, а не функцией
//1. +полноценная смерть
//2. +замена текстур юнитов
//3. +анимации
//4. +новый противник после смерти прошлого
//5. +-переход между границами экрана
//6. +окно с выбором улучшений после убийства
//7. пассивные способности с иконками
//8. активные способности с кд
//9. инвентарь и подбираемый лут
//10. зелья лечения
//11. комнаты с сундуками
//12. комнаты с боссами
//13. +скейл противников по уровню
//14. +новая формула расчета урона
//15. +-новые виды противников
//16. гейм овер
//17. смена локации
//18. новые боссы
//19. выбор персонажа
//20. больше способностей
//21. +--цифры урона, надпись уклонения,красный цвет для крита.
//22. изменить шрифт
//23. показ иконки следущего противника
// 24. +-звук удара
// 25. +музыка
// 26. карта выбора пути из slay the spire
// 27. +--главное меню
// 28. настройки
// 29. рестарт
// 30. прокачка
// 31. визуальное отображение после гейм овера (как в Plants vs Zombies)

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.gameScreen = new GameScreen(batch);
		this.mainMenu = new MainMenu();
		gameActive = false;

		MainMenu.create();
		UpgradeStats.create();
		TextonBuff.createCard();
		Veha.create();




	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (gameActive) {
			this.gameScreen.render();
		}
		else {
			this.mainMenu.render();
			}
	}

	@Override
	public void dispose () {
		batch.dispose();

	}

	public static void gameStart() {
		gameActive = !gameActive;
	}
}
