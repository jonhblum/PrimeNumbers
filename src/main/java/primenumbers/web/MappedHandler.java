package primenumbers.web;

import java.util.HashMap;

// map the different implementations
// so they can be picked from the Rest Api

public class MappedHandler<T> {

	public static final String DEFAULT = "DEFAULT";

	private HashMap<String, T> handlerMap;
	
	public MappedHandler(T handler) {

		handlerMap = new HashMap<String, T>();
		
		handlerMap.put(DEFAULT, handler);		
	}
	
	public MappedHandler<T> registerHandler(String handlerName, T handler)
	{
		handlerMap.put(handlerName, handler);
		
		return this;
	}

	public T getHandler(String handlerName)
	{
		return handlerMap.get(handlerName);
	}
}
