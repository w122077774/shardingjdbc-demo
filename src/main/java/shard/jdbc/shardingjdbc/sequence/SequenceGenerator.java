package shard.jdbc.shardingjdbc.sequence;


import shard.jdbc.shardingjdbc.constant.DbAndTableEnum;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/3/25 9:07
 * @className SequenceGenerator
 * @desc 序列生成器接口
 */
public interface SequenceGenerator {


    /**
     * 查redis方式 key前缀（形如：tableName_dbIndex_tbIndex_）
     * @param targetEnum
     * @param dbIndex
     * @param tbIndex
     * @return
     */
    String getNextVal(DbAndTableEnum targetEnum, int dbIndex, int tbIndex);
}
