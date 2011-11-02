package com.edl;

import java.util.Random;

import org.apache.http.impl.conn.tsccm.WaitingThread;
import org.cocos2d.actions.CCTimer;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import android.sax.StartElementListener;
import android.util.Log;
import android.view.MotionEvent;

public class GameLayer extends CCLayer{
	private CCSprite xwarm;
	private CCSprite xdream;
	private CCSprite bapple = CCSprite.sprite("blue_apple.png");
	private CCSprite bapple1 = CCSprite.sprite("blue_apple.png");
	private CCSprite bapple2 = CCSprite.sprite("blue_apple.png");
	private CCSprite Sapple = CCSprite.sprite("Apple_logo.png");
	private CCSprite Capple = CCSprite.sprite("apple.png");
	private CCSprite male1 = CCSprite.sprite("male.png");
	private CCSprite male2 = CCSprite.sprite("male.png");
	private CCSprite male3 = CCSprite.sprite("male.png");
	private CCSprite female = CCSprite.sprite("female.png");
	private CCSprite arrow = CCSprite.sprite("arrow.png");
	private int actionIndex = 2;
	private static final String TAG = "Cocos2D";
	private float initialX = 0;  
    private float initialY = 0;  
    private float deltaX = 0;  
    private float deltaY = 0; 
    private float accum = 0;
	
	public static CCScene scene()
	{
	    CCScene scene = CCScene.node();
	    CCLayer layer = new GameLayer();
	    //CCLayer tiledLayer = CCLayer.node(); 
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    CCSprite bg = CCSprite.sprite("porsche.png");
	    bg.setPosition(winSize.width/2, winSize.height/2);
	    
	    scene.addChild(bg);
	    scene.addChild(layer);
	 
	    return scene;
	}
	
	protected GameLayer()
	{
		//super(color);
		
		this.setIsTouchEnabled(true);

		
		xwarm = CCSprite.sprite("blue_apple.png");
		xdream = CCSprite.sprite("Apple_logo.png");
		CCSprite apple = CCSprite.sprite("blue_apple.png");
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    //CCSprite apple1 = CCSprite.sprite("blue_apple.png");
	    //CCSprite apple2 = CCSprite.sprite("Apple_logo.png");
	 
	    //apple1.setPosition(CGPoint.ccp(apple1.getContentSize().width / 2.0f, winSize.height / 2.0f + 50));
	    
	    //apple2.setPosition(CGPoint.ccp(apple2.getContentSize().width / 2.0f, winSize.height / 2.0f - 50));
	 
	    //addChild(apple1);
	    //addChild(apple2);
	    
	    CCMenuItemImage apple1 = CCMenuItemImage.item("blue_apple.png", "blue_apple.png", this, "test1");
	    CCMenuItemImage apple2 = CCMenuItemImage.item("Apple_logo.png", "Apple_logo.png", this, "test2");
	    
	    CCMenu menu = CCMenu.menu(apple1, apple2);
	    
	    menu.setPosition(CGPoint.ccp(apple.getContentSize().width / 2.0f, winSize.height / 2.0f + 50));
	    menu.alignItemsVertically(60);
	    addChild(menu, 1);
	    this.runAction(CCSequence.actions(CCDelayTime.action(100.0f), CCCallFunc.action(this, "gameover")));
	    this.schedule("update");
	    
	}
	
	
	public void test1()
	{
		removeChild(male1, true);
		removeChild(male2, true);
		removeChild(male3, true);
		removeChild(Sapple, true);
		removeChild(bapple, true);
		removeChild(Capple, true);
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		CCSprite icon1 = CCSprite.sprite("blue_apple.png");
		bapple = CCSprite.sprite("blue_apple.png");
		icon1.setPosition(winSize.width - 2*(bapple.getContentSize().width / 2.0f), winSize.height- 2*(bapple.getContentSize().height / 2.0f));
		bapple.setPosition(winSize.width / 2.0f, winSize.height/ 2.0f);
		male1.setPosition(winSize.width/2 + male1.getContentSize().width, winSize.height / 2.0f);
		male2.setPosition(winSize.width/2 + 2*(male2.getContentSize().width), (male2.getContentSize().height/2)+ (male2.getContentSize().height));
		male3.setPosition(winSize.width - 2*(male3.getContentSize().width / 2.0f), (male3.getContentSize().height / 2.0f));
		arrow.setPosition(winSize.width/2 + 2*(male2.getContentSize().width), (male2.getContentSize().height/2)+ (male2.getContentSize().height));
		male1.setOpacity(0);
		male2.setOpacity(0);
		male3.setOpacity(0);
		addChild(male1);
		addChild(male2);
		addChild(male3);
		addChild(bapple);
		addChild(icon1);
		addChild(arrow);
		actionIndex = 0;
	}
	
	public void test2()
	{
		removeChild(bapple, true);
		removeChild(Sapple, true);
		removeChild(Capple, true);
		removeChild(male1, true);
		removeChild(male2, true);
		removeChild(male3, true);
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		Sapple = CCSprite.sprite("Apple_logo.png");
		CCSprite icon2 = CCSprite.sprite("Apple_logo.png");
		icon2.setPosition(winSize.width - 2*(Sapple.getContentSize().width / 2.0f), winSize.height- 2*(Sapple.getContentSize().height / 2.0f));
		Sapple.setPosition(winSize.width / 2.0f, winSize.height/ 2.0f);
		addChild(Sapple);
		addChild(icon2);
		actionIndex = 1;
	}

	
	@Override
    public boolean ccTouchesBegan(MotionEvent event) {
		return super.ccTouchesBegan(event);
	}

	
	/*@Override
    public boolean ccTouchesBegan(MotionEvent event) {
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		//if(event.getX() <= winSize.width/2 && event.getY() <= winSize.height/2){
        int pointerIndex = event.getPointerId(event.getActionIndex());
        CCSprite apple1 = CCSprite.sprite("blue_apple.png");
	    CCSprite apple2 = CCSprite.sprite("Apple_logo.png");
            CGPoint location =  CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(pointerIndex),event.getY(pointerIndex)));
            Log.d(TAG, String.format("touch %d=(%f,%f)", event.getActionIndex(), location.x,location.y));
            
            if(Math.sqrt(Math.pow(location.x - apple1.getContentSize().width / 2.0f,2)) <=apple1.getContentSize().width / 2.0f  
              && Math.sqrt(Math.pow(location.y - apple1.getContentSize().height/2.0f + 50,2)) <=apple1.getContentSize().height / 2.0f ){
            switch (event.getActionIndex()) {
        case 0:
            xwarm.setPosition(winSize.width/2, winSize.height/2);
            addChild(xwarm);
            break;
        case 1:
            xdream.setPosition(location);
            addChild(xdream);
            break;
        default:
            break;
    }
            }
            return super.ccTouchesBegan(event);
    } */
	
	
	
    @Override
    public boolean ccTouchesMoved(MotionEvent event) {  
               
    //    int pointerIndex = event.getPointerId(event.getActionIndex());
 //  CGPoint location =  CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(pointerIndex),event.getY(pointerIndex)));
   //         Log.d(TAG, String.format("move %d=(%f,%f)", event.getActionIndex(), location.x,location.y));
    //	if(actionIndex == 1 || actionIndex == 0){
    //		return super.ccTouchesMoved(event);
    	int pointerIndex = event.getPointerId(event.getActionIndex());
    	CCSprite size = CCSprite.sprite("Apple_logo.png");
    	CGRect bappleRect = CGRect.make(0,0,0,0);
    	CGRect SappleRect = CGRect.make(0,0,0,0);
    	CGRect CappleRect = CGRect.make(0,0,0,0);
    	CGPoint loc =  CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(pointerIndex),event.getY(pointerIndex)));
    	Log.d(TAG, String.format("touch %d=(%f,%f)", event.getActionIndex(), loc.x,loc.y));
    	
    	if(actionIndex == 0){
    		bappleRect = CGRect.make(bapple.getPosition().x - (bapple.getContentSize().width / 2.0f),
            bapple.getPosition().y - (bapple.getContentSize().height / 2.0f),
            bapple.getContentSize().width,
            bapple.getContentSize().height);
    	}
    	if(actionIndex == 8){
    		bappleRect = CGRect.make(bapple.getPosition().x - (bapple.getContentSize().width / 2.0f),
            bapple.getPosition().y - (bapple.getContentSize().height / 2.0f),
            bapple.getContentSize().width,
            bapple.getContentSize().height);
    	}
    	if(actionIndex == 9){
    		bappleRect = CGRect.make(bapple.getPosition().x - (bapple.getContentSize().width / 2.0f),
            bapple.getPosition().y - (bapple.getContentSize().height / 2.0f),
            bapple.getContentSize().width,
            bapple.getContentSize().height);
    	}
    	if(actionIndex == 1){
    		SappleRect = CGRect.make(Sapple.getPosition().x - (Sapple.getContentSize().width / 2.0f),
            Sapple.getPosition().y - (Sapple.getContentSize().height / 2.0f),
            Sapple.getContentSize().width,
            Sapple.getContentSize().height);
    	}
    	if(actionIndex == 3){
    		CappleRect = CGRect.make(Capple.getPosition().x - (Capple.getContentSize().width / 2.0f),
            Capple.getPosition().y - (Capple.getContentSize().height / 2.0f),
            Capple.getContentSize().width,
            Capple.getContentSize().height);
    	}
    	CGRect touchRect = CGRect.make(loc.x - (bapple.getContentSize().width / 2.0f),
                loc.y - (bapple.getContentSize().height / 2.0f),
                bapple.getContentSize().width,
                bapple.getContentSize().height);
    	
    	
           if((CGRect.intersects(bappleRect, touchRect)) || (CGRect.intersects(SappleRect, touchRect)) || (CGRect.intersects(CappleRect, touchRect))){
            	if (loc.x <= 2*(size.getContentSize().width)){
            		return super.ccTouchesMoved(event);
            	}else{
          			int pointCnt = event.getPointerCount();
          			for (int i = 0; i < pointCnt; i++) {
          				CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(i),event.getY(i)));
          				Log.d(TAG, String.format("move %d=(%f,%f)", i, location.x, location.y));
              
          				switch (actionIndex) {
          				case 0:
          					bapple.setPosition(location);
          					break;
          				case 1:
          					Sapple.setPosition(location);
          					break;
          				case 3:
          					Capple.setPosition(location);
          					break;
          				case 8:
          					bapple.setPosition(location);
          					break;
          				case 9:
          					bapple.setPosition(location);
          					break;
          				default:
          					break;
          				}
          			}
            	}
            	}
    	//}
            	return super.ccTouchesMoved(event);
    }

    @Override
    public boolean ccTouchesEnded(MotionEvent event) {
        Log.d(TAG, "ccTouchesEnded");
//      int pointerIndex = event.getPointerId(event.getActionIndex());
//  CGPoint location =  CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(pointerIndex),event.getY(pointerIndex)));
//  Log.d(TAG, String.format("touch %d=(%f,%f)", event.getActionIndex(), location.x,location.y));     

        switch (actionIndex) {
        	case 0:
            //removeChild(bapple, true);
        		break;
        	case 1:
            //removeChild(Sapple, true);
        		break;
        	default:
        		break;
        }
    
        return super.ccTouchesEnded(event);
    }
    
    
    public void gameover(){
    	CCDirector.sharedDirector().replaceScene(GameOverLayer.scene("Timeover"));
    }
    
    public void update(float dt){
    	CGRect bappleRect = CGRect.make(0,0,0,0);
    	CGRect SappleRect = CGRect.make(0,0,0,0);
    	CGRect male1Rect = CGRect.make(0,0,0,0);
    	CGRect male2Rect = CGRect.make(0,0,0,0);
    	CGRect male3Rect = CGRect.make(0,0,0,0);
    	CGSize winSize = CCDirector.sharedDirector().displaySize();
    	if(actionIndex == 3){
    		CCDirector.sharedDirector().replaceScene(GameOverLayer.scene("Timeover"));
    	}
    	if(actionIndex == 0){
    		bappleRect = CGRect.make(bapple.getPosition().x - (bapple.getContentSize().width / 2.0f),
            bapple.getPosition().y - (bapple.getContentSize().height / 2.0f),
            bapple.getContentSize().width,
            bapple.getContentSize().height);
    	}
    	if(actionIndex == 1){
    		SappleRect = CGRect.make(Sapple.getPosition().x - (Sapple.getContentSize().width / 2.0f),
            Sapple.getPosition().y - (Sapple.getContentSize().height / 2.0f),
            Sapple.getContentSize().width,
            Sapple.getContentSize().height);
    		}
    	if(actionIndex == 8){
    		bappleRect = CGRect.make(bapple.getPosition().x - (bapple.getContentSize().width / 2.0f),
            bapple.getPosition().y - (bapple.getContentSize().height / 2.0f),
            bapple.getContentSize().width,
            bapple.getContentSize().height);
    	}
    	if(actionIndex == 9){
    		bappleRect = CGRect.make(bapple.getPosition().x - (bapple.getContentSize().width / 2.0f),
            bapple.getPosition().y - (bapple.getContentSize().height / 2.0f),
            bapple.getContentSize().width,
            bapple.getContentSize().height);
    	}
    	male1Rect = CGRect.make(male1.getPosition().x - (male1.getContentSize().width / 2.0f),
                male1.getPosition().y - (male1.getContentSize().height / 2.0f),
                male1.getContentSize().width,
                male1.getContentSize().height);
    	male2Rect = CGRect.make(male2.getPosition().x - (male2.getContentSize().width / 2.0f),
                male2.getPosition().y - (male2.getContentSize().height / 2.0f),
                male2.getContentSize().width,
                male2.getContentSize().height);
    	male3Rect = CGRect.make(male3.getPosition().x - (male3.getContentSize().width / 2.0f),
                male3.getPosition().y - (male3.getContentSize().height / 2.0f),
                male3.getContentSize().width,
                male3.getContentSize().height);
    	if((CGRect.intersects(male1Rect, bappleRect)) && (actionIndex ==0)){
    		actionIndex = 8;
    	}
    	if((CGRect.intersects(male2Rect, bappleRect)) && (actionIndex ==8)){
    		actionIndex = 9;
    	}
    	if((CGRect.intersects(male3Rect, bappleRect)) && (actionIndex ==9)){
    		removeChild(male1, true);
    		removeChild(male2, true);
    		removeChild(male3, true);
    		female.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
    		addChild(female);
    		removeChild(bapple, true);
    		Capple.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
    		addChild(Capple);
    		actionIndex = 3;
    			}
    		
    }
}
