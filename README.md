# VKShell2

## Anatomy
### Modes:
```
$mode[state]@position>mode[state]@position> command
or
$mode[state]@position>mode[state]@position>mode[state]@position: data
$> command
```
#### examples:
- `$VKShell@/d/music>vk[username]@audio?id=1>audio>` is bad
- `$VKShell@/d/music>vk[username]>audio@id=1>`  
good, **default mode** at _d/music_ > **vk mode** with _username_ > **audio mode** at _id=1_
- `$VKShell@/d/music>vk[username]@audio?id=1>`  
good, **default mode** at _d/music_ > **vk mode** with _username_ at _audio?id=1_
- `$VKShell@/d/music>vk[username]>audio[playing]@id=1: Blabla -- blablabla`  
good, **default mode** at _d/music_ > **vk mode** with _username_ > **audio mode** is _playing something_ at _id=1_ 

### Commands:
```
user@localhost:~/folder$ cat username.txt > VKShell echo 
$> echo -u username | login | audio
```
#### Command is:
- command name
- ?options 
- ?arguments (String only)
- ?pipe

```
$Default Mode> command -boolean -param1 1 -param2 2 arg1 arg2
$Selection Mode> %anything% or $Selection Mode> %special exit command%
$Audio Mode>Sorting Mode> 1playlist 2playlist 3pla -command -command
```

## Login procedure fallback
1. Execute API Command (if failed, then)
1. Read token key from file (...)
1. Get token key **@ auth page** _and write it to file_
1. Read cookies from file
1. Get cookies **@ login page** _and write them to file_

## Command list

- `$VKShell> start` // iternal
- `$VKShell> echo` // echo command
- `$VKShell> audio => $VKShell>audio>`
- `$VKShell> login (username) (?password) (?appid) => $VKShell>vk[username]>`

## Use cases:
### Files:
1. Get source1, source2, target
1. n times: Take source1[n], normalize, copy to target
1. n times: Take source2[n], normalize, check hash, copy to target
1. write down equal hashes

### Audio:
#### download
```
$VKShell> login username // login
$VKShell>vk[username]> audio // go to audio mode
$VKShell>vk[username]>audio> download // download all
```
#### sort to playlists
```
$VKShell> cd d:/music
$VKShell@d:/music> audio // audio mode
$VKShell@d:/music>audio> sort // sort special mode, current dir
$VKShell@d:/music>audio>sort: Artist 1 - Song 1 // first song
$> -p // play
$VKShell@d:/music>audio>sort[playing]: Artist 1 - Song 1
$> s3 f -r Bad Artist 1 - Song 1  // to playlist s3, to playlist f, rename
$VKShell@d:/music>audio>sort[playing]: Artist 1 - Song 2 // next song
$> -e -s // exit, stop
$VKShell@d:/music>audio> login username // login
$VKShell[username]@d:/music>audio> sync // move song to playlist s3, f, rename
$VKShell[username]@d:/music>audio synced!
```

```
git checkout --orphan newBranch
git add -A  # Add all files and commit them
git commit
git branch -D master  # Deletes the master branch
git branch -m master  # Rename the current branch to mast
```













