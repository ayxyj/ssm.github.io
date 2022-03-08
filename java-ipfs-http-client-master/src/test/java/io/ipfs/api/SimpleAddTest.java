package io.ipfs.api;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import io.ipfs.api.NamedStreamable.FileWrapper;
import io.ipfs.multiaddr.MultiAddress;

/**
 *
 * ipfs daemon --enable-pubsub-experiment &
 *
 * ipfs pin rm `ipfs pin ls -qt recursive`
 *
 * ipfs --api=/ip4/127.0.0.1/tcp/5001 add -r src/test/resources/html
 *
 */
public class SimpleAddTest {

    static final Map<String, String> cids = new LinkedHashMap<>();
    static {
        cids.put("index.html", "QmVts3YjmhsCSqMv8Thk1CCy1nnpCbqEFjbkjS7PEzthZE");
        cids.put("html", "QmfPRsChuVCsnN4PGLaDCngvjqCoc9KGkxea4hgiq6qitk");
    }

    IPFS ipfs = new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001"));

    /**
     * 添加一个文件到IPFS网络
     * @throws Exception
     */
    @Test
    public void testSingle() throws Exception {
        Path path = Paths.get("src/test/resources/html/index.html");
        NamedStreamable file = new FileWrapper(path.toFile());
        List<MerkleNode> tree = ipfs.add(file);
        printTree(tree);
        Assert.assertEquals(1, tree.size());
        Assert.assertEquals("index.html", tree.get(0).name.get());
        Assert.assertEquals(cids.get("index.html"), tree.get(0).hash.toBase58());
    }

    /**
     * 上传文件 --  包装
     * @throws Exception
     */
    @Test
    public void testSingleWrapped() throws Exception {

        Path path = Paths.get("src/test/resources/html/index.html");
        NamedStreamable file = new FileWrapper(path.toFile());
        List<MerkleNode> tree = ipfs.add(file, true);
        System.out.println(tree.size());
        printTree(tree);
        Assert.assertEquals(2, tree.size());
        Assert.assertEquals("index.html", tree.get(0).name.get());
        Assert.assertEquals(cids.get("index.html"), tree.get(0).hash.toBase58());
        System.out.println(tree.get(0).hash.toBase58());
        System.out.println(tree.get(0).name.get());
    }

    /**
     * 上传文件
     * @throws Exception
     */
    @Test
    public void testSingleOnlyHash() throws Exception {

        Path path = Paths.get("src/test/resources/html/index.html");
        NamedStreamable file = new FileWrapper(path.toFile());
        List<MerkleNode> tree = ipfs.add(file, false, true);
        printTree(tree);
        Assert.assertEquals(1, tree.size());
        Assert.assertEquals("index.html", tree.get(0).name.get());
        Assert.assertEquals(cids.get("index.html"), tree.get(0).hash.toBase58());
    }

    /**
     * 上传文件夹
     * @throws Exception
     */
    @Test
    public void testRecursive() throws Exception {

        Path path = Paths.get("src/test/resources/html");
        System.out.println(path.toFile());
        NamedStreamable file = new FileWrapper(path.toFile());
        List<MerkleNode> tree = ipfs.add(file);
        System.out.println(tree.size());
        printTree(tree);
        Assert.assertEquals(14, tree.size());
        Assert.assertEquals("html/css", tree.get(7).name.get());
        Assert.assertEquals(cids.get("html"), tree.get(7).hash.toBase58());
    }

    /**
     * 上传文件夹
     * @throws Exception
     */
    @Test
    public void testRecursiveOnlyHash() throws Exception {

        Path path = Paths.get("src/test/resources/html");
        NamedStreamable file = new FileWrapper(path.toFile());
        List<MerkleNode> tree = ipfs.add(file, false, true);
        printTree(tree);
        Assert.assertEquals(14, tree.size());
        Assert.assertEquals("html/css", tree.get(7).name.get());
        Assert.assertEquals(cids.get("html"), tree.get(7).hash.toBase58());
    }

    /**
     * 输出Tree
     * @param tree
     */
    public void printTree(List<MerkleNode> tree){
        for (MerkleNode merkleNode : tree) {
            System.out.println(merkleNode);
        }
    }
}
