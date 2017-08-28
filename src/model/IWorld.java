package model;

import java.util.Collection;
import java.util.function.Predicate;

import gameobject.IGameObject;
import gameobject.Template;
import util.Observable;
import util.Replicate;

public interface IWorld extends WorldInfo, Replicate<WorldInfo>, Observable<IGameObject> {

    IGameObject addChampion(Template template);

    @Override
    IGameObject addObject(Template template);

    @Override
    IGameObject addObject(Template template, double x, double y);

    @Override
    IGameObject getObject(int id);
    
    Collection<IGameObject> getIf(Predicate<IGameObject> predicate);
    
    Collection<IGameObject> removeIf(Predicate<IGameObject> predicate);
    
}
