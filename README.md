# VKShell2
```
git checkout --orphan newBranch
git add -A  # Add all files and commit them
git commit
git branch -D master  # Deletes the master branch
git branch -m master  # Rename the current branch to mast
```

## Anatomy
```
$VKShell[%username%]@%position%>%mode%[%mode_state]>%special_mode%: %some_data%
$VKShell> = default mode, state = username, position = local directory

$VKShell@/d/music>vk[username]@audio?id=1>audio> = wrong
**$VKShell@/d/music>vk[username]>audio@id=1>**
$VKShell@/d/music>vk[username]@audio?id=1>audio>
$VKShell@/d/music>vk[username]@audio?id=1>audio>
```

## Commands

- `$VKShell> echo` 
- `$VKShell> audio => $VKShell>audio>`
- `$VKShell> login (username) (?password) (?appid) => $VKShell[username]>`

#Outdated

## Use cases:
### Audio:
#### download
```
$VKShell> audio // go to audio mode
$VKShell>audio> login username // login
$VKShell[username]>audio> download // download all
```
#### sort to playlists
```
$VKShell> cd d:/music
$VKShell@d:/music> audio // audio mode
$VKShell@d:/music>audio> sort // sort special mode, current dir
$VKShell@d:/music>audio>sort: Artist 1 - Song 1 // first song
> -p // play
$VKShell@d:/music>audio>sort[playing]: Artist 1 - Song 1
> s3 f -r Bad Artist 1 - Song 1  // to playlist s3, to playlist f, rename
$VKShell@d:/music>audio>sort[playing]: Artist 1 - Song 2 // next song
> -e -s // exit, stop
$VKShell@d:/music>audio> login username // login
$VKShell[username]@d:/music>audio> sync // move song to playlist s3, f, rename
$VKShell[username]@d:/music>audio synced!
```


















