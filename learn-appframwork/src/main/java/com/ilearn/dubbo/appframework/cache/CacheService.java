package com.ilearn.dubbo.appframework.cache;

/**
 * @author George 2014-11-12 下午2:54:29 <br>
 *         缓存接口
 */
public interface CacheService {
	/**
	 * 存入键值对数据到缓存中
	 *
	 * @param key   键
	 * @param value 值
	 * @return true表示存入成功, 否则反之
	 */
	public <T> boolean put(String key, T value);

	/**
	 * 存入键值对数据到缓存中
	 *
	 * @param key   键
	 * @param value 值
	 * @param exp   过期时期(秒)
	 * @return true表示存入成功, 否则反之
	 */
	public <T> boolean put(String key, T value, int exp);

	/**
	 * 根据指定的键,取得对应的值
	 *
	 * @param key 键
	 * @return 键对应的值
	 */
	public <T> T get(String key);

	/**
	 * 根据指定的键,删除对应的值
	 *
	 * @param key 键
	 * @return true:表示删除成功,false:删除未成功
	 */
	public boolean remove(String key);

	/**
	 * 设置key过期时间
	 * @param key 键
	 * @param exp 过期时间
	 * @return
	 */
	public  boolean expire(String key, int exp);

	/**
	 * 判断key是否存在
	 * @param key 键
	 * @return true:表示存在KEY,false:不存在
	 */
	public boolean isExist(String key);
}
