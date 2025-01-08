import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-hoops-page',
  templateUrl: './hoops-page.component.html',
  styleUrl: './hoops-page.component.css'
})
export class HoopsPageComponent implements OnInit, OnDestroy{
  private unityInstance: any;

  ngOnInit(): void {
    this.loadUnityGame();
  }

  loadUnityGame(): void {
    const buildUrl = 'assets/Hoops';
    const loaderUrl = `${buildUrl}/HoopsBuild.loader.js`;

    const config = {
      dataUrl: `${buildUrl}/HoopsBuild.data.br`,
      frameworkUrl: `${buildUrl}/HoopsBuild.framework.js.br`,
      codeUrl: `${buildUrl}/HoopsBuild.wasm.br`,
      streamingAssetsUrl: 'StreamingAssets',
      companyName: 'DefaultCompany',
      productName: 'UnityGame',
      productVersion: '1.0',
      showBanner: (msg: any, type: any) => { console.log(msg, type); },
    };

    const script = document.createElement('script');
    script.src = loaderUrl;
    script.onload = () => {
      (window as any).createUnityInstance(
        document.querySelector('#unity-canvas'),
        config,
        (progress: number) => {
          const progressBarFull = document.querySelector('#unity-progress-bar-full') as HTMLElement;
          if (progressBarFull) progressBarFull.style.width = `${100 * progress}%`;
        }
      ).then((unityInstance: { SetFullscreen: (arg0: number) => void; Quit: () => void; }) => {
        this.unityInstance = unityInstance; 
        const fullscreenButton = document.querySelector('#unity-fullscreen-button');
        if (fullscreenButton) {
          fullscreenButton.addEventListener('click', () => {
            unityInstance.SetFullscreen(1);
          });
        }
      }).catch((message: any) => {
        alert(message);
      });
    };
    document.body.appendChild(script);
  }

  ngOnDestroy(): void {
    if (this.unityInstance) {
      this.unityInstance.Quit(); 
    }
  }
}
