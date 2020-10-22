package com.example.smartcityb_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 16:08
 */
public class CommitList {
    /**
     * num : 1
     * commit : 祝福祖国
     * commitTime : 2020-10-01 11:24:01
     * reviewer : abc
     */
    //{
    //            "num": 1,
    //            "commit": "祝福祖国",
    //            "commitTime": "2020-10-01 11:24:01",
    //            "reviewer": "abc"
    //        }

    private int num;
    private String commit;
    private String commitTime;
    private String reviewer;

    public CommitList() {
    }

    public CommitList(int num, String commit, String commitTime, String reviewer) {
        this.num = num;
        this.commit = commit;
        this.commitTime = commitTime;
        this.reviewer = reviewer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
