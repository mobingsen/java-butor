package org.plus.hdp.module.hottop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * @author mbs on 2021-06-02 8:44
 */
public class HotTopJob {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration(true);

        // conf.set("mapreduce.framework.name", "local");
        conf.set("mapreduce.app-submission.cross-platform", "true");
        // conf.set("fs.defaultFS", "local");

        final String[] remainingArgs = new GenericOptionsParser(args).getRemainingArgs();

        Job job = Job.getInstance(conf);

        job.setJarByClass(HotTopJob.class);
        job.setJobName("Hot Top N");
        // 只能集群运行
        job.addCacheFile(new Path("/data/dict/dict.txt").toUri());

        FileInputFormat.addInputPath(job, new Path(remainingArgs[0]));

        final Path out = new Path(remainingArgs[1]);
        final FileSystem outFileSystem = out.getFileSystem(conf);
        if (outFileSystem.exists(out)) {
            outFileSystem.delete(out, true);
        }
        FileOutputFormat.setOutputPath(job, out);

        job.setMapperClass(HotTopMapper.class);
        job.setMapOutputKeyClass(HotTopKey.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setPartitionerClass(HotTopPartitioner.class);
        job.setSortComparatorClass(HotTopSortComparator.class);

        job.setGroupingComparatorClass(HotTopGroupingComparator.class);

        job.setReducerClass(HotTopReduce.class);

        job.waitForCompletion(true);
    }
}
